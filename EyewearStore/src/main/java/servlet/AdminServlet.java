package servlet;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.OrderDao;
import dao.ProductDao;
import dao.UserDao;
import model.Order;
import model.Product;
import connection.DBConnection;

public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/pages/admin/amministrazione.jsp");
            return;
        }

        switch (action) {
        	case "addProduct":
        		addProduct(request, response);
        		break;
            
            case "updatePrice":
                updateProductPrice(request, response);
                break;
            case "deleteProduct":
                deleteProduct(request, response);
                break;
            case "updateStatus":
                updateOrderStatus(request, response);
                break;
            case "deleteOrdine":
                deleteOrder(request, response);
                break;
            case "deleteUtente":
                deleteUtente(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/pages/admin/amministrazione.jsp");
        }
    }
    
    private void addProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String brand = request.getParameter("brand");
        String modello = request.getParameter("modello");
        double prezzo = Double.parseDouble(request.getParameter("prezzo"));
        String sesso = request.getParameter("sesso");
        Part filePart = request.getPart("immagine");
        String fileName = filePart.getSubmittedFileName();


        // Salva il file nella cartella del progetto
        String filePath = "../media/" + File.separator + fileName;
        filePart.write(filePath);

        // Creazione di un nuovo oggetto Product con i valori ricevuti dalla form
        Product product = new Product();
        product.setNome(nome);
        product.setBrand(brand);
        product.setModello(modello);
        product.setPrezzo(prezzo);
        product.setSesso(sesso);
        product.setData_Inserimento(LocalDate.now()+"");
        product.setImage(fileName); // Salva il nome del file nel database

        // Logica per aggiungere il nuovo prodotto al database
        ProductDao productDao = new ProductDao(DBConnection.getConnection());
        productDao.addProduct(product);

        response.sendRedirect(request.getContextPath() + "/pages/admin/amministrazione.jsp");
    }

    // Metodo ausiliario per estrarre il nome del file da un Part
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }

    private void updateProductPrice(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        double newPrice = Double.parseDouble(request.getParameter("newPrice"));

        // Logica per aggiornare il prezzo del prodotto con l'ID specificato nel database
        ProductDao productDao = new ProductDao(DBConnection.getConnection());
        Product product = productDao.getSingleProduct(productId);
        if (product != null) {
            product.setPrezzo(newPrice);
            productDao.updateProduct(product);
        }

        response.sendRedirect(request.getContextPath() + "/pages/admin/amministrazione.jsp");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));

        // Logica per eliminare il prodotto con l'ID specificato dal database
        ProductDao productDao = new ProductDao(DBConnection.getConnection());
        productDao.deleteProduct(productId);

        response.sendRedirect(request.getContextPath() + "/pages/admin/amministrazione.jsp");
    }

    private void updateOrderStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        // Logica per aggiornare lo stato dell'ordine con l'ID specificato nel database
        OrderDao orderDao = new OrderDao(DBConnection.getConnection());
        Order order = orderDao.getOrderById(orderId);
        if (order != null) {
            String currentStatus = order.getStato();
            String newStatus = "";

            // Verifica lo stato attuale dell'ordine e imposta il nuovo stato in base alla logica descritta
            if (currentStatus.equals("Ordinato")) {
                newStatus = "In Consegna";
            } else if (currentStatus.equals("In Consegna")) {
                newStatus = "Consegnato";
            } else if (currentStatus.equals("Consegnato")) {
                // Se lo stato è già "Consegnato", non fare nulla
                response.sendRedirect(request.getContextPath() + "/pages/admin/amministrazione.jsp");
                return;
            }

            // Aggiorna lo stato dell'ordine nel database
            order.setStato(newStatus);
            orderDao.updateOrder(order);
        }

        response.sendRedirect(request.getContextPath() + "/pages/admin/amministrazione.jsp");
    }



    private void deleteOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        // Logica per eliminare l'ordine con l'ID specificato dal database
        OrderDao orderDao = new OrderDao(DBConnection.getConnection());
        orderDao.cancelOrder(orderId);

        response.sendRedirect(request.getContextPath() + "/pages/admin/amministrazione.jsp");
    }

    private void deleteUtente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));

        // Logica per eliminare l'utente con l'ID specificato dal database
        UserDao userDao = new UserDao(DBConnection.getConnection());
        userDao.deleteUser(userId);
    }
}