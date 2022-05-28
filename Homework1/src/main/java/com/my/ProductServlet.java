package com.my;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/productsList")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        List<Product> products = getProductsList();
        for (Product p : products) {
            pw.println(p);
        }
        pw.close();
    }

    private List<Product> getProductsList() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "beans", 130));
        products.add(new Product(2, "carrot", 110));
        products.add(new Product(3, "beat", 85));
        products.add(new Product(4, "apple", 105));
        products.add(new Product(5, "bread", 60));
        products.add(new Product(6, "chocolate", 120));
        products.add(new Product(7, "chicken", 370));
        products.add(new Product(8, "banana", 160));
        products.add(new Product(9, "milk", 150));
        products.add(new Product(10, "cake", 350));
        return products;
    }

}
