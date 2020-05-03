package com.telega;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot{

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            Message message  = update.getMessage();
            if(message!=null && message.hasText()) {
                String chatId = message.getChatId().toString();
                String text = message.getText();
                if (text.startsWith("/")) {
                    switch (text) {
                        case "/help":
                            sendMsg(chatId, "0 помощь блеееааать");
                            break;
                        case "/author":
                            sendMsg(chatId, "ЧУМИД ебать");
                            break;
                        case "/доллар":
                            try {
                                sendMsg(chatId, Currencies.getCurrency("доллар").toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case "/евро":
                            try {
                                sendMsg(chatId, Currencies.getCurrency("евро"));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case "/темпа":
                            try {
                                sendMsg(chatId, Currencies.getModel().getTemp());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case "/temperature":
                            try {
                                sendMsg(chatId, Currencies.getJson());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        default:
                            sendMsg(chatId, "это дзюцу не изучено");
                            break;
                    }
                } else if (text.toLowerCase().contains("пидор")) {
                    sendInlineMessage(chatId);
                } else {
                    sendMsgExample(chatId, "получай в ответку ебать:\n" + text);

                }
            }
        } else if(update.hasCallbackQuery()){
            sendMsg(update.getCallbackQuery().getMessage().getChatId().toString(), update.getCallbackQuery().getData());
        }
    }

    public void setButtons(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> list = new ArrayList<>();

        KeyboardRow keyboardRow = new KeyboardRow();

        keyboardRow.add(new KeyboardButton("/help"));
        keyboardRow.add(new KeyboardButton("/author"));

        list.add(keyboardRow);

        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add(new KeyboardButton("/доллар"));
        keyboardRow1.add(new KeyboardButton("/евро"));

        list.add(keyboardRow1);

        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add(new KeyboardButton("/темпа"));

        list.add(keyboardRow2);

        replyKeyboardMarkup.setKeyboard(list);

        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }

    public synchronized InlineKeyboardMarkup setInline(){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();
        buttons1.add(new InlineKeyboardButton("осуждать").setCallbackData("Осуждаю"));
        buttons1.add(new InlineKeyboardButton("презирать").setCallbackData("Презираю"));
        buttons.add(buttons1);
        inlineKeyboardMarkup.setKeyboard(buttons);
        return inlineKeyboardMarkup;
    }

    public synchronized void sendInlineMessage(String chatId){
        SendMessage sendMessage = new SendMessage().setChatId(chatId).setText("Осуждаю такиие высказывания");
        try {
            execute(sendMessage.setReplyMarkup(setInline()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sendMsgExample(String chatId, String text){

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(new KeyboardButton("/таджикитан"));
        keyboardRow.add(new KeyboardButton("/ауф"));

        keyboardRows.add(keyboardRow);

        replyKeyboardMarkup.setKeyboard(keyboardRows);

        try {
            execute(new SendMessage(chatId, text).setReplyMarkup(replyKeyboardMarkup));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sendMsg(String chatId, String text){
        SendMessage sendMessage = new SendMessage(chatId, text);
        sendMessage.enableMarkdown(true);
        try {
            setButtons(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "Xev11Bot";
    }

    @Override
    public String getBotToken() {
        return "1161506086:AAFJbCWrXpCIlgsD956tO3kJu1uYUGezdoc";
    }
}

