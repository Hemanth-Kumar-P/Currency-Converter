import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class CurrencyConverterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Get the amount and currencies from the request
        double amount = Double.parseDouble(request.getParameter("amount"));
        String fromCurrency = request.getParameter("fromCurrency");
        String toCurrency = request.getParameter("toCurrency");

        // Calculate the converted amount
        double convertedAmount = convertCurrency(amount, fromCurrency, toCurrency);

        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Generate HTML response with CSS
        out.println("<html><head><style>");
        out.println("body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: linear-gradient(135deg, #ff9a9e 0%, #fad0c4 55%, #fad0c4 100%); display: flex; justify-content: center; align-items: center; height: 100vh; }");
        out.println(".result-container { background-color: #fff; padding: 30px; border-radius: 15px; box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2); text-align: center; }");
        out.println("h1 { color: #333; }");
        out.println("p { font-size: 20px; color: #333; }");
        out.println(".highlight { color: #007bff; font-weight: bold; font-size: 24px; }");
        out.println("a { display: inline-block; margin-top: 20px; padding: 10px 20px; background-color: #007bff; color: white; text-decoration: none; border-radius: 8px; }");
        out.println("a:hover { background-color: #0056b3; }");
        out.println("</style></head><body>");
        out.println("<div class='result-container'>");
        out.println("<h1>Currency Converter Result</h1>");
        out.println("<p>" + amount + " " + fromCurrency + " = <span class='highlight'>" + String.format("%.2f", convertedAmount) + " " + toCurrency + "</span></p>");
        out.println("<a href='index.html'>Convert Another</a>");
        out.println("</div>");
        out.println("</body></html>");
    }

    private double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        double exchangeRate = getExchangeRate(fromCurrency, toCurrency);
        return amount * exchangeRate;
    }

    private double getExchangeRate(String fromCurrency, String toCurrency) {
        // Hardcoded exchange rates for demonstration purposes
        if (fromCurrency.equals("USD") && toCurrency.equals("EUR")) return 0.85;
        if (fromCurrency.equals("EUR") && toCurrency.equals("USD")) return 1.18;
        if (fromCurrency.equals("USD") && toCurrency.equals("INR")) return 74.5;
        if (fromCurrency.equals("INR") && toCurrency.equals("USD")) return 0.013;
        if (fromCurrency.equals("EUR") && toCurrency.equals("INR")) return 87.5;
        if (fromCurrency.equals("INR") && toCurrency.equals("EUR")) return 0.011;
        if (fromCurrency.equals("JPY") && toCurrency.equals("USD")) return 0.0091;
        if (fromCurrency.equals("GBP") && toCurrency.equals("USD")) return 1.38;
        if (fromCurrency.equals("AUD") && toCurrency.equals("USD")) return 0.74;
        if (fromCurrency.equals("CAD") && toCurrency.equals("USD")) return 0.80;
        if (fromCurrency.equals("CHF") && toCurrency.equals("USD")) return 1.10;
        return 1; // Default to 1:1 if same currency
    }
}
