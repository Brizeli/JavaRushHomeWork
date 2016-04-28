package com.javarush.test.level30.lesson15.big01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        ConsoleHelper.writeMessage("Введите порт");
        int socketPort = ConsoleHelper.readInt();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(socketPort);
            ConsoleHelper.writeMessage("Сервер запущен");
            while (true) {
                Socket socket = serverSocket.accept();
                new Handler(socket).start();
            }
        } catch (Exception e) {
            try {
                serverSocket.close();
            } catch (Exception e1) {
                ConsoleHelper.writeMessage(e1.getMessage());
            }
        }
    }

    public static void sendBroadcastMessage(Message message) {
        for (Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
            try {
                entry.getValue().send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Не удалось отправить сообщение юзеру " + entry.getKey());
            }
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            ConsoleHelper.writeMessage("Установлено соединение с "+socket.getRemoteSocketAddress());
            Connection connection=null;
            String userName=null;
            try {
                connection = new Connection(socket);
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED,userName));
                sendListOfUsers(connection,userName);
                serverMainLoop(connection,userName);
            } catch (Exception e) {
                try {
                    connection.close();
                } catch (IOException e1) {
                    ConsoleHelper.writeMessage(e1.getMessage());
                }
                ConsoleHelper.writeMessage("произошла ошибка при обмене данными с удаленным адресом");
            }
            connectionMap.remove(userName);
            sendBroadcastMessage(new Message(MessageType.USER_REMOVED,userName));
            ConsoleHelper.writeMessage("Соединение с удаленным адресом закрыто");
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message userNameResponseMessage = connection.receive();
                if (userNameResponseMessage.getType() != MessageType.USER_NAME) continue;
                String userName = userNameResponseMessage.getData();
                if (userName.equals("") || connectionMap.containsKey(userName)) continue;
                connectionMap.put(userName, connection);
                connection.send(new Message(MessageType.NAME_ACCEPTED));
                return userName;
            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
                if (!entry.getKey().equals(userName))
                    connection.send(new Message(MessageType.USER_ADDED, entry.getKey()));
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT)
                    sendBroadcastMessage(new Message(MessageType.TEXT,userName+": "+message.getData()));
                else ConsoleHelper.writeMessage("Не текстовое сообщение");
            }
        }
    }
}
