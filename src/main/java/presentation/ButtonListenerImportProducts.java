package presentation;

import business.MenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class ButtonListenerImportProducts implements ActionListener {
    private List<MenuItem> products;
    private List<MenuItem> productsAux;

    public ButtonListenerImportProducts(List<MenuItem> products, List<MenuItem> productsAux) {
        this.products = products;
        this.productsAux = productsAux;
    }

    public void actionPerformed(ActionEvent e) {
        products.addAll(productsAux);
    }
}
