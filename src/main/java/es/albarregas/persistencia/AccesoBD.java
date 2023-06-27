/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.persistencia;

import es.albarregas.beans.Ave;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Jesus
 */
@WebServlet(name = "AccesoBD", urlPatterns = {"/acceso"})
public class AccesoBD extends HttpServlet {

    DataSource dataSource;
    
    @Override
    public void init(ServletConfig config) {
        try {
            Context contextoInicial = new InitialContext();
            dataSource = (DataSource)contextoInicial.lookup("java:comp/env/jdbc/APool");
        } catch (NamingException ex) {
            System.out.println("Se ha producido un error en la conexión a la base de datos");
            ex.printStackTrace();
        }
        
    }
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = null;
        try {
            Connection conexion = null;
            Statement sentencia = null;
            PreparedStatement preparada = null;
            ResultSet resultado = null;
            Ave ave = null;
            List<Ave> aves = null;

            conexion = dataSource.getConnection();
            String anilla = request.getParameter("anilla");

            String sql = null;

            
            if (request.getParameter("una") != null) {
                if (anilla != null) {
                    sql = "SELECT * FROM aves WHERE anilla = ?";
                    preparada = conexion.prepareStatement(sql);
                    preparada.setString(1, anilla);
                    try {
                        resultado = preparada.executeQuery();
                        resultado.next();
                        ave = new Ave();
                        ave.setAnilla(resultado.getString("anilla"));
                        ave.setEspecie(resultado.getString(2));
                        ave.setLugar(resultado.getString("lugar"));
                        ave.setFecha(resultado.getString("fecha"));
                        request.setAttribute("una", ave);
                        url = "unRegistro.jsp";
                    } catch (SQLException e) {
                        
                        request.setAttribute("error", "La anilla " + anilla + " no se encuentra en la base de datos");
                        url = "error.jsp";
                    } finally {
                        if (resultado != null) {
                            resultado.close();
                        }
                        if (conexion != null) {
                            conexion.close();
                        }
                    }
                }
            } else if (request.getParameter("rand") != null) {
                try {
                    int numero = Integer.parseInt(anilla);
                    if (numero > 0) {
                        sql = "SELECT * FROM aves ORDER BY rand() LIMIT " + numero;
                        try {
                            sentencia = conexion.createStatement();
                            resultado = sentencia.executeQuery(sql);
                            aves = new ArrayList<>();
                            while (resultado.next()) {
                                ave = new Ave();
                                ave.setAnilla(resultado.getString(1));
                                ave.setEspecie(resultado.getString(2));
                                ave.setLugar(resultado.getString(3));
                                ave.setFecha(resultado.getString(4));
                                aves.add(ave);
                            }
                            request.setAttribute("aves", aves);
                            url = "variosRegistros.jsp";
                        } catch (SQLException e) {
                            request.setAttribute("error", "Ha ocurrido un error al acceder a la tabla");
                            url = "error.jsp";
                        }
                    } else {
                        request.setAttribute("error", "El número tiene que ser mayor que 0");
                        url = "error.jsp";
                    }

                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Tienes que introducir un número válido");
                    url = "error.jsp";
                } finally {
                    if (resultado != null) {
                        resultado.close();
                    }
                    if (conexion != null) {
                        conexion.close();
                    }
                }

            } else {
                try {
                    sql = "SELECT * FROM aves";
                    sentencia = conexion.createStatement();
                    resultado = sentencia.executeQuery(sql);
                    aves = new ArrayList<>();
                    while (resultado.next()) {
                        ave = new Ave();
                        ave.setAnilla(resultado.getString(1));
                        ave.setEspecie(resultado.getString(2));
                        ave.setLugar(resultado.getString(3));
                        ave.setFecha(resultado.getString(4));
                        aves.add(ave);
                    }
                    request.setAttribute("aves", aves);
                    url = "variosRegistros.jsp";

                } catch (SQLException e) {
                    request.setAttribute("error", "Ha ocurrido un error al acceder a la tabla");
                    url = "error.jsp";

                } finally {
                    if (resultado != null) {
                        resultado.close();
                    }
                    if (conexion != null) {
                        conexion.close();
                    }
                }

            }

        } catch (SQLException e) {
            request.setAttribute("error", "Ha ocurrido un error al acceder a la base de datos");
            url = "error.jsp";

        }

        request.getRequestDispatcher(url).forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
