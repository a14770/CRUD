import com.sun.net.httpserver.HttpServer;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(
                new InetSocketAddress(8080), 0);

        /// HOME
        server.createContext("/", exchange -> {
            String html = """
                        <html>
                        <head>
                            <meta charset="UTF-8">
                            <title>Sistema de Clientes</title>
                            <style>
                                body {
                                    font-family: Arial, sans-serif;
                                    background-color: #f5f6fa;
                                    text-align: center;
                                    margin: 0;
                                }
                                header {
                                    background-color: #2f3640;
                                    color: white;
                                    padding: 20px;
                                }

                                h1 {
                                    margin: 0;
                                }
                                .container {
                                    margin-top: 50px;
                                }
                                .card {
                                    display: inline-block;
                                    background: white;
                                    padding: 30px;
                                    margin: 20px;
                                    border-radius: 10px;
                                    box-shadow: 0 2px 8px rgba(0,0,0,0.1);
                                    width: 200px;
                                }
                                .card a {
                                    display: block;
                                    text-decoration: none;
                                    color: white;
                                    background: #40739e;
                                    padding: 10px;
                                    border-radius: 5px;
                                    margin-top: 15px;
                                }
                                .card a:hover {
                                    background: #273c75;
                                }
                            </style>
                        </head>
                        <body>
                            <header>
                                <h1>Sistema de Clientes</h1>
                                <p>Gestão simples com Java + MySQL</p>
                            </header>
                            <div class="container">
                                <div class="card">
                                    <h3>Ver Clientes</h3>
                                    <p>Consultar lista completa</p>
                                    <a href="/clientes">Abrir</a>
                                </div>
                                <div class="card">
                                    <h3>+ Novo Cliente</h3>
                                    <p>Adicionar novo registo</p>
                                    <a href="/novo">Criar</a>
                                </div>
                            </div>
                        </body>
                        </html>
                    """;
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, html.getBytes().length);
            exchange.getResponseBody().write(html.getBytes());
            exchange.close();
        });

        server.start();
        System.out.println("Servidor em http://localhost:8080");
    }
}
