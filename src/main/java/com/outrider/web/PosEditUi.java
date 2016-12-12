package com.outrider.web;

import static com.outrider.cons.OutriderConstants.*;
import com.outrider.domain.entity.PointOfSale;
import com.outrider.domain.repository.PointOfSaleRepository;
import com.vaadin.data.Validator;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Jonatan on 09/12/2016.
 */
@SpringComponent
@UIScope
public class PosEditUi extends VerticalLayout {

    private PointOfSaleRepository repository;

    private PointOfSale pointOfSale;

    private final TextField tradingName;
    private final TextField phone;
    private final TextField address;
    private final TextField openingHours;
    private final Button save;
    private final Button cancel;
    private final Button delete;
    private final CssLayout actions;
    private final Notification notificationMessage;

    @Autowired
    public PosEditUi(PointOfSaleRepository repository) {

        this.repository = repository;

        notificationMessage = new Notification(EMPTY_VALUE, EMPTY_VALUE, Notification.TYPE_ERROR_MESSAGE);
        notificationMessage.setDelayMsec(10000);
        notificationMessage.setPosition(Position.TOP_CENTER);

        tradingName = new TextField(PointOfSaleConstants.TRADING_NAME_LABEL);
        tradingName.addValidator(new NullValidator(PointOfSaleConstants.TRANDING_NAME_NULL_MESSAGE, false));
        tradingName.setImmediate(true);
        tradingName.setRequired(true);
        tradingName.setValidationVisible(true);
        tradingName.setWidth(100, Unit.PERCENTAGE);

        phone = new TextField(PointOfSaleConstants.PHONE_NUMBER_LABEL);
        phone.setInputPrompt(PointOfSaleConstants.PHONE_INPUT);
        phone.addValidator(new RegexpValidator(RegexPatternConstants.PHONE_REGEX_PATTERN, PointOfSaleConstants.PHONE_FORMAT_MESSAGE));
        phone.setImmediate(true);
        phone.setValidationVisible(true);

        address = new TextField(PointOfSaleConstants.ADDRESS_LABEL);
        openingHours = new TextField(PointOfSaleConstants.OPENING_HOURS_LABEL);

        save = new Button(ActionConstants.SAVE_LABEL, FontAwesome.SAVE);
        cancel = new Button(ActionConstants.CANCEL_LABEL);
        delete = new Button(ActionConstants.DELETE_LABEL, FontAwesome.TRASH_O);
        actions = new CssLayout(save, cancel, delete);

        address.setWidth(100, Unit.PERCENTAGE);

        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        save.addClickListener(e -> {
            try {
                tradingName.validate();
            } catch (Validator.EmptyValueException e1) {
                notificationMessage.setCaption(ErrorConstants.REQUIRED_FIELD);
                notificationMessage.setDescription(PointOfSaleConstants.TRANDING_NAME_NULL_MESSAGE);
                notificationMessage.show(Page.getCurrent());
                return;
            }
            try{
                phone.validate();
            } catch (Validator.InvalidValueException e2) {
                notificationMessage.setCaption(ErrorConstants.INVALID_FORMAT);
                notificationMessage.setDescription(PointOfSaleConstants.PHONE_FORMAT_MESSAGE);
                notificationMessage.show(Page.getCurrent());
                return;
            }
            repository.save(pointOfSale);
        });

        delete.addClickListener(e -> repository.delete(pointOfSale));

        FormLayout content = new FormLayout();
        content.addComponent(tradingName);
        content.addComponent(phone);
        content.addComponent(address);
        content.addComponent(openingHours);
        content.addComponent(actions);
        content.setSpacing(true);
        content.setMargin(true);

        Panel panel = new Panel(PointOfSaleConstants.POINT_OF_SALE_DETAIL_LABEL);
        panel.setContent(content);

        addComponents(panel);
        setSpacing(true);
        setWidth(100, Unit.PERCENTAGE);

        setVisible(false);
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editCustomer(PointOfSale c) {
        final boolean persisted = c.getId() != null;
        if (persisted) {
            pointOfSale = repository.findOne(c.getId());
        }
        else {
            pointOfSale = c;
        }
        delete.setVisible(persisted);
        BeanFieldGroup.bindFieldsUnbuffered(pointOfSale, this);
        setVisible(true);
        save.focus();
        tradingName.selectAll();
    }

    public void setChangeHandler(ChangeHandler h) {
        save.addClickListener(e -> h.onChange());
        delete.addClickListener(e -> h.onChange());
        cancel.addClickListener(e -> h.onChange());
    }
}