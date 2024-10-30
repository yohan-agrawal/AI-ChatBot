import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ChatBotGUI extends JFrame {
    private JTextArea chatArea;
    private JTextField userInputField;
    private JButton sendButton;
    private ChatBot chatBot;

    public ChatBotGUI() {
        setTitle("ChatBot");
        setSize(500, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel headerLabel = new JLabel("Welcome to ChatBot", JLabel.CENTER);
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 16));
        chatArea.setBackground(new Color(245, 245, 245));
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        add(chatScrollPane, BorderLayout.CENTER);

        userInputField = new JTextField();
        userInputField.setFont(new Font("Arial", Font.PLAIN, 16));
        userInputField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        sendButton = new JButton("Send");
        sendButton.setFont(new Font("Arial", Font.BOLD, 16));
        sendButton.setBackground(new Color(70, 130, 180));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(new Color(230, 230, 250));
        inputPanel.add(userInputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        chatBot = new ChatBot();

        sendButton.addActionListener(new SendButtonListener());
        userInputField.addActionListener(new SendButtonListener());

        setVisible(true);
    }

    private class SendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userInput = userInputField.getText().trim();
            if (!userInput.isEmpty()) {
                chatArea.append("You: " + userInput + "\n");
                String response = chatBot.getResponse(userInput);
                chatArea.append("ChatBot: " + response + "\n\n");
                userInputField.setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatBotGUI::new);
    }
}

class ChatBot {
    private final Map<String, String> responses;

    public ChatBot() {
        responses = new HashMap<>();
        loadResponses();
    }

    private void loadResponses() {
        responses.put("hello", "Hi there! How can I help you today?");
        responses.put("how are you", "I'm good, thanks! How about you?");
        responses.put("what is your name", "I'm Ruby AI, your friendly assistant!");
        responses.put("bye", "Goodbye! Have a great day!");
        responses.put("help", "Sure, I'm here to help. Please ask me your question!");
        responses.put("time", "I don’t have a clock, but I can try to help you with other questions!");
    }

    public String getResponse(String input) {
        String lowerCaseInput = input.toLowerCase();

        for (String key : responses.keySet()) {
            if (lowerCaseInput.contains(key)) {
                return responses.get(key);
            }
        }
        return "I'm not sure how to respond to that. Can you try changing the input?";
    }
}