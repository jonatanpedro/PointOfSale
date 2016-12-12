package com.outrider.cons;

/**
 * Created by jonatan on 12/12/16.
 */
public interface OutriderConstants {

    String EMPTY_VALUE = "";
    String SEPARATOR = " - ";

    interface LoginConstants{
        String LOGIN = "Login";
        String LOGOUT = "Logout";
        String USER_LABEL = "User:";
        String USER_INPUT = "username";
        String PASSWORD_LABEL = "Password:";
        String LOGIN_MAIN_CAPTION = "<h1>Point of Sales Service</h1><p>Please enter with your credentials:</p>";
        String LOGIN_FAIL_MESSAGE = "The given user or/and password is incorrect!";
        String LOGIN_FAIL_MESSAGE_TITLE = "Login failed";
        String DEVELOPED_BY_LABEL = "<p>Developed by:<a href=\"mailto:jonatanpedro@gmail.com\">Jonatan Pedro da Silva</a></p>";
    }

    interface PageView{
        String LOGIN_PAGE = "/login";
        String MAIN_PAGE = "/posmain";
    }

    interface ActionConstants{
        String SAVE_LABEL = "Save";
        String DELETE_LABEL = "Delete";
        String CANCEL_LABEL = "Cancel";
    }

    interface ErrorConstants{
        String REQUIRED_FIELD = "Required Field";
        String INVALID_FORMAT = "Invalid Format";
    }

    interface RegexPatternConstants{
        String PHONE_REGEX_PATTERN = "^\\(\\d{2}\\)\\s\\d{4}-\\d{4}";
    }

    interface PointOfSaleConstants{
        String ADD_BUTTON_LABEL = "Add Point of sale";
        String FILTER_INPUT = "Filter by trading name";
        String TRADING_NAME_LABEL = "Trading Name:";
        String PHONE_NUMBER_LABEL = "Phone Number:";
        String PHONE_INPUT = "(###) ####-####";
        String ADDRESS_LABEL = "Address:";
        String OPENING_HOURS_LABEL = "Opening Hours:";
        String TRANDING_NAME_NULL_MESSAGE = "The Tranding name must has a value!";
        String PHONE_FORMAT_MESSAGE = "The Phone's Format number mismatch with '(###) ####-####'";
        String POINT_OF_SALE_DETAIL_LABEL = "Point of Sales Detail";
    }
}
