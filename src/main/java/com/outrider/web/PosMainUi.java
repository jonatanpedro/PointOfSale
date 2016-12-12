package com.outrider.web;

import static com.outrider.cons.OutriderConstants.*;
import com.outrider.domain.entity.PointOfSale;
import com.outrider.domain.entity.User;
import com.outrider.domain.repository.PointOfSaleRepository;
import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import static com.outrider.cons.OutriderConstants.EMPTY_VALUE;
import static com.outrider.cons.OutriderConstants.PointOfSaleConstants;

/**
 * Created by jonatan on 09/12/2016.
 */
@SpringUI(path = "/posmain")
@Theme("reindeer")
public class PosMainUi extends UI{

    private final PointOfSaleRepository repo;
    private final PosEditUi editor;
    private final Grid grid;
    private final TextField filter;
    private final Button addNewBtn;

    @Autowired
    public PosMainUi(PointOfSaleRepository repo, PosEditUi editor) {
        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid();
        this.filter = new TextField();
        this.addNewBtn = new Button(PointOfSaleConstants.ADD_BUTTON_LABEL, FontAwesome.PLUS);
    }

    @Override
    protected void init(VaadinRequest request) {

        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        MenuBar mainMenu = new MenuBar();
        MenuBar.MenuItem userBar = mainMenu.addItem(user.getUsername(), null, null);
        userBar.addItem(LoginConstants.LOGOUT,logout());
        mainMenu.setWidth(100, Unit.PERCENTAGE);

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        VerticalLayout vertLayout = new VerticalLayout(actions, grid);

        HorizontalLayout mainLayout = new HorizontalLayout(vertLayout,editor);
        VerticalLayout mainVertLayout = new VerticalLayout(mainMenu,mainLayout);
        //mainVertLayout.setSizeFull();

        setContent(mainVertLayout);

        actions.setSpacing(true);

        vertLayout.setSpacing(true);
        vertLayout.setHeight(100, Unit.PERCENTAGE);
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        mainLayout.setHeight(100, Unit.PERCENTAGE);

        grid.setHeight(100, Unit.PERCENTAGE);
        grid.setWidth(800, Unit.PIXELS);

        filter.setInputPrompt(PointOfSaleConstants.FILTER_INPUT);
        filter.addTextChangeListener(e -> listPos(e.getText()));
        grid.addSelectionListener(e -> {
            if (e.getSelected().isEmpty()) {
                editor.setVisible(false);
            }
            else {
                editor.editCustomer((PointOfSale) grid.getSelectedRow());
            }
        });

        addNewBtn.addClickListener(e -> editor.editCustomer(new PointOfSale(EMPTY_VALUE, EMPTY_VALUE, EMPTY_VALUE, EMPTY_VALUE)));
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listPos(filter.getValue());
        });

        listPos(null);
    }

    void listPos(String text) {
        if (StringUtils.isEmpty(text)) {
            grid.setContainerDataSource(new BeanItemContainer(PointOfSale.class, repo.findAll()));
        }
        else {
            grid.setContainerDataSource(new BeanItemContainer(PointOfSale.class, repo.findByTradingNameStartsWithIgnoreCase(text)));
        }
    }

    MenuBar.Command logout(){
        return selectedItem -> {
            Page.getCurrent().setLocation(PageView.LOGIN_PAGE);
            SecurityContextHolder.clearContext();
            VaadinSession.getCurrent().close();
        };
    }
}
