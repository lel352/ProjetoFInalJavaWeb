package projetofinal.controle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import projetofinal.controle.bancodao.CervejaDao;
import projetofinal.controle.bancodao.CheckInDao;
import projetofinal.controle.bancodao.TipoCervejaDao;
import projetofinal.modelo.Login;
import projetofinal.modelo.Cerveja;
import projetofinal.modelo.CheckIn;
import projetofinal.modelo.TipoCerveja;
import projetofinal.modelo.Usuario;


/**
 *
 * @author Delphi
 */
@WebServlet(name = "ControleServlet", urlPatterns = {"/controleservlet", "/cadastrotipo", "/salvartipo", "/cadastrocerveja", "/salvarcerveja","/cadastrousuario", "/salvarusuario","/login","/entrarlogin","/consultartipo","/editartipo",
                                                     "/excluirtipo", "/consultarcerveja","/editarcerveja","/excluircerveja","/checkin","/salvarcheckin",
                                                     "/listacheckin", "/origemcheckin","/sair","/index"})
public class ControleServlet extends HttpServlet {
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");  
        
        String chamada = null;
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (usuario == null){
           usuario = new Usuario();
           //request.getSession().setAttribute("Usuario", usuario);
           if (!request.getRequestURI().endsWith("/entrarlogin")) {
              request.getSession().invalidate();
              request.getSession(true);
              response.sendRedirect(chamadaLogin(request, response));    
              return;  
           }   
        }
        
        if (request.getRequestURI().endsWith("/cadastrotipo")) {
           chamada = chamadaCadastroTipo(request, response);
        }
        else if (request.getRequestURI().endsWith("/salvartipo")) {
           chamada = chamadaSalvarTipo(request, response);
        }
        else if (request.getRequestURI().endsWith("/consultartipo")) {
           chamada = chamadaConsultarTipo(request, response);
        }
        else if (request.getRequestURI().endsWith("/editartipo")) {
           chamada = chamadaEditarTipo(request, response);
        }
        else if (request.getRequestURI().endsWith("/excluirtipo")) {
           chamada = chamadaExcluirTipo(request, response);
        }
        else if (request.getRequestURI().endsWith("/cadastrocerveja")) {
           chamada = chamadaCadastroCerveja(request, response);
        }
        else if (request.getRequestURI().endsWith("/salvarcerveja")) {
           chamada = chamadaSalvarCerveja(request, response);
        }
        else if (request.getRequestURI().endsWith("/consultarcerveja")) {
           chamada = chamadaConsultarCerveja(request, response);
        }
        else if (request.getRequestURI().endsWith("/editarcerveja")) {
           chamada = chamadaEditarCerveja(request, response);
        }
        else if (request.getRequestURI().endsWith("/excluircerveja")) {
           chamada = chamadaExcluirCerveja(request, response);
        }
        else if (request.getRequestURI().endsWith("/cadastrousuario")) {
           chamada = chamadaCadastroUsuario(request, response, usuario);
        }
        else if (request.getRequestURI().endsWith("/salvarusuario")) {
           chamada = chamadaSalvarUsuario(request, response, usuario);
        }
        else if (request.getRequestURI().endsWith("/login")) {
           chamada = chamadaLogin(request, response);
        }
        else if (request.getRequestURI().endsWith("/entrarlogin")) {
            chamada = chamadaConfimaLogin(request, response);
            if (("/principal.jsp").equals(chamada)) {
               response.sendRedirect(chamada);
               return;
            }   
        }  
        else if (request.getRequestURI().endsWith("/checkin")) {
           chamada = chamadaCheckin(request, response);
        }
        else if (request.getRequestURI().endsWith("/salvarcheckin")) {
           chamada = chamadaSalvarCheckin(request, response, usuario);
        }
        else if (request.getRequestURI().endsWith("/listacheckin")) {
           chamada = chamadaListaCheckin(request, response, usuario);
        }
        else if (request.getRequestURI().endsWith("/origemcheckin")) {
           chamada = chamadaOrigemCheckin(request, response); 
        }
        else if (request.getRequestURI().endsWith("/sair")) {
           request.getSession().invalidate();
           request.getSession(true);
           response.sendRedirect(chamadaLogin(request, response));
           return;
        }
        
        if (chamada == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            request.getRequestDispatcher(chamada).forward(request, response);
        }        
    }
    
    /**
     * Método para chamar cadastro de tipo de cerveja cadastrotipo.jsp
     * 
     * @param request
     * @param response
     * @return jsp de cadastro de cerveja
     */
    private String chamadaCadastroTipo(HttpServletRequest request, HttpServletResponse response){
        ControleTipo controleTipo = new ControleTipo();
        int codigoSequencial;
        try {
            codigoSequencial = controleTipo.maxCodigoTipo()+1;//Próximo código para cadastro 
        } catch (Exception ex) {
            codigoSequencial = 0;
            Logger.getLogger(ControleServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("erro", ex.getMessage());
        }
        request.setAttribute("codigoSequencial", codigoSequencial); 
        return "/cadastrotipo.jsp";
    }
    
    
    /**
     * Método para salvar alterações do tipo
     * 
     * @param request
     * @param response
     * @return 
     */    
    private String chamadaSalvarTipo(HttpServletRequest request, HttpServletResponse response){
        String codigo = request.getParameter("codigoTipo");
        String nome = request.getParameter("descricao");
                
        TipoCerveja tipoCerveja = new TipoCerveja();
        tipoCerveja.setCodigo(Integer.valueOf(codigo));
        tipoCerveja.setNome(nome);        
       
        ControleTipo controleTipo = new ControleTipo();
        try {
            controleTipo.gravaTipo(tipoCerveja);
            request.setAttribute("efetuado", true);
            request.setAttribute("mensagem", "Tipo de Cerveja "+nome+" salvo com sucesso !!!");
        }
        catch (Exception ex) {
            Logger.getLogger(ControleServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("codigoSequencial", 0); 
            request.setAttribute("tipoCerveja", tipoCerveja);
            request.setAttribute("erro", ex.getMessage());
            request.setAttribute("efetuado", false);
            return "/cadastrotipo.jsp";
        }               
        return chamadaCadastroTipo(request, response);
    }
    
    /**
     *Método para efetuar a consultar de tipo
     * 
     * @param request
     * @param response
     * @return 
     */
    private String chamadaConsultarTipo(HttpServletRequest request, HttpServletResponse response){
        String consulta = request.getParameter("consulta");
        
        ControleTipo controleTipo = new ControleTipo();
        try {
            ArrayList<TipoCerveja> listaTipoCerveja = controleTipo.consultarTipo(consulta);
            request.setAttribute("lista", listaTipoCerveja);            
        } catch (Exception ex) {
            Logger.getLogger(ControleServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("erro", ex.getMessage());
        }
        return "/consultatipo.jsp";
    }
    
    /**
     * Método para editar tipo de Cerveja
     * 
     * @param request
     * @param response
     * @return 
     */
    private String chamadaEditarTipo(HttpServletRequest request, HttpServletResponse response){
        String consulta = request.getParameter("codigo");
        
        TipoCervejaDao tipoCervejaDao = new TipoCervejaDao();
        try {
            request.setAttribute("codigoSequencial", 0);
            TipoCerveja tipoCerveja = tipoCervejaDao.getTipoCerveja(Integer.valueOf(consulta));
            request.setAttribute("tipoCerveja", tipoCerveja);            
        } catch (Exception ex) {
            Logger.getLogger(ControleServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("erro", ex.getMessage());
        }
        return "/cadastrotipo.jsp";
    }
        
    /**
     * Método para excluir tipo de cerveja
     * 
     * @param request
     * @param response
     * @return 
     */
    private String chamadaExcluirTipo(HttpServletRequest request, HttpServletResponse response){
        String consulta = request.getParameter("codigo");
        
        ControleTipo controleTipo = new ControleTipo();
        try {
            controleTipo.deletar(Integer.valueOf(consulta));
        } catch (Exception ex) {
            Logger.getLogger(ControleServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("erro", ex.getMessage());
        }
        return chamadaConsultarTipo(request, response);
    }
    
    
    /**
     * Método para chamar cadastro de Cerveja - cadastrocerveja.jsp
     * 
     * @param request
     * @param response
     * @return cadastrocerveja.jsp
     */
    private String chamadaCadastroCerveja(HttpServletRequest request, HttpServletResponse response){
        ControleCerveja controleCerveja = new ControleCerveja();
        int codigoSequencial;
        try {
            codigoSequencial = controleCerveja.maxCodigoCerveja()+1;//Próximo código para cadastro 
        } catch (Exception ex) {
            codigoSequencial = 0;
            Logger.getLogger(ControleServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("erro", ex.getMessage());
        }
        TipoCervejaDao tipoCervejaDao = new TipoCervejaDao();
        ArrayList<TipoCerveja> lista = tipoCervejaDao.getAll();
        
        request.setAttribute("codigoSequencial", codigoSequencial); 
        request.setAttribute("listaTipoCerveja", lista); 
        return "/cadastrocerveja.jsp";
    }
     
   
    /**
     * Método para salvar dados da cerveja
     * 
     * @param request
     * @param response
     * @return 
     */
    private String chamadaSalvarCerveja(HttpServletRequest request, HttpServletResponse response){
        String codigo = request.getParameter("codigoCerveja");
        String nome = request.getParameter("descricao");
        String tipoCerveja = request.getParameter("tipo");
        
        Cerveja cerveja = new Cerveja();
        cerveja.setCodigo(Integer.valueOf(codigo));
        cerveja.setCodigoTipo(Integer.valueOf(tipoCerveja));
        cerveja.setNome(nome);
        
        ControleCerveja controleCerveja = new ControleCerveja();
        try {
            controleCerveja.gravaCerveja(cerveja);
            request.setAttribute("efetuado", true);
            request.setAttribute("mensagem", "Cerveja "+nome+" salva com sucesso !!!");
        }
        catch (Exception ex) {
            Logger.getLogger(ControleServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("cerveja", cerveja);
            request.setAttribute("erro", ex.getMessage());
            request.setAttribute("efetuado", false);
            TipoCervejaDao tipoCervejaDao = new TipoCervejaDao();
            ArrayList<TipoCerveja> lista = tipoCervejaDao.getAll();
        
            request.setAttribute("codigoSequencial", 0); 
            request.setAttribute("listaTipoCerveja", lista); 
            return "/cadastrocerveja.jsp";
        }                   
        
       return chamadaCadastroCerveja(request, response); 
    }    
    
    /**
     * Método para chamar consultar Cerveja
     * 
     * @param request
     * @param response
     * @return 
     */
    private String chamadaConsultarCerveja(HttpServletRequest request, HttpServletResponse response){
        String consulta = request.getParameter("consulta");
        
        ControleCerveja controleCerveja = new ControleCerveja();
        try {
            ArrayList<Cerveja> listaCerveja = controleCerveja.consultarCerveja(consulta);
            request.setAttribute("lista", listaCerveja);            
        } catch (Exception ex) {
            Logger.getLogger(ControleServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("erro", ex.getMessage());
        }
        request.setAttribute("origem", 1);
        return "/consultacerveja.jsp";
    }
    
     /**
     * Método para editar Cerveja
     * 
     * @param request
     * @param response
     * @return 
     */
    private String chamadaEditarCerveja(HttpServletRequest request, HttpServletResponse response){
        String consulta = request.getParameter("codigo");
        
        CervejaDao cervejaDao = new CervejaDao();
        try {
            Cerveja cerveja = cervejaDao.getCerveja(Integer.valueOf(consulta));
            TipoCervejaDao tipoCervejaDao = new TipoCervejaDao();
            ArrayList<TipoCerveja> lista = tipoCervejaDao.getAll(); 
            
            request.setAttribute("cerveja", cerveja);            
            request.setAttribute("codigoSequencial", 0);
            request.setAttribute("listaTipoCerveja", lista); 
            
        } catch (Exception ex) {
            Logger.getLogger(ControleServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("erro", ex.getMessage());
            return "/consutarcerveja.jsp";
        }
        return "/cadastrocerveja.jsp";
    }
        
    /**
     * Método para excluir Cerveja
     * 
     * @param request
     * @param response
     * @return 
     */
    private String chamadaExcluirCerveja(HttpServletRequest request, HttpServletResponse response){
        String consulta = request.getParameter("codigo");
        
        ControleCerveja controleCerveja = new ControleCerveja();
        try {
            controleCerveja.deletar(Integer.valueOf(consulta));
        } catch (Exception ex) {
            Logger.getLogger(ControleServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("erro", ex.getMessage());
        }
        return chamadaConsultarCerveja(request, response);
    }
    
    /**
     * Método para chamar cadastro de Usuario cadastrousuario.jsp
     * 
     * @param request
     * @param response
     * @return jsp de cadastro de cerveja
     */
    private String chamadaCadastroUsuario(HttpServletRequest request, HttpServletResponse response, Usuario usuario){
        request.setAttribute("usuario", usuario);        
        return "/cadastrousuario.jsp";
    }
    
    
    /**
     * Método para salvar alterações do tipo
     * 
     * @param request
     * @param response
     * @return 
     */    
    private String chamadaSalvarUsuario(HttpServletRequest request, HttpServletResponse response, Usuario usuario){
        String codigo = request.getParameter("codigo");
        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String email = request.getParameter("email");
        String nascimento = request.getParameter("nascimento");
        
        SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy"); //você pode usar outras máscaras 
        java.util.Date dataUsuario = null;
        try {
            dataUsuario = sdf1.parse(nascimento);
        } catch (ParseException ex) {
            Logger.getLogger(ControleServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("erro", "Erro na data");
            request.setAttribute("efetuado", false);
            return chamadaCadastroUsuario(request, response, usuario);
        }
       
        
        if (usuario == null)
            usuario.setCodigo(Integer.valueOf(codigo));
       
        usuario.setLogin(login);        
        usuario.setNome(nome);        
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setNascimento(dataUsuario);
       
        ControleUsuario controleUsuario = new ControleUsuario();
        try {
            controleUsuario.grava(usuario);
            request.setAttribute("efetuado", true);
            request.setAttribute("mensagem", "Dados do Usuário "+nome+" salvo com sucesso !!!");
        }
        catch (Exception ex) {
            Logger.getLogger(ControleServlet.class.getName()).log(Level.SEVERE, null, ex);
            //request.setAttribute("usuario", usuario);
            request.setAttribute("erro", ex.getMessage());
            request.setAttribute("efetuado", false);
            //return "/cadastrousuario.jsp";
        }               
        return chamadaCadastroUsuario(request, response, usuario);
    }    
    
    private String chamadaLogin(HttpServletRequest request, HttpServletResponse response){
        return "login.jsp";
    }
    
    private String chamadaConfimaLogin(HttpServletRequest request, HttpServletResponse response){
        String userlogin = request.getParameter("login");
        String usersenha = request.getParameter("senha");
        
        Login login = new Login();
        login.setLogin(userlogin);
        login.setSenha(usersenha);
        
        ControleLogin controleLogin = new ControleLogin();
        try {
            controleLogin.confirmarLogn(login);
            ControleUsuario controleUsuario = new ControleUsuario();
            Usuario usuario = controleUsuario.getUsuario(login);
            request.getSession().setAttribute("usuario", usuario);
            
        } catch (Exception ex) {
            Logger.getLogger(ControleServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("erro", ex.getMessage());
            return "/login.jsp";
        }
        return "principal.jsp";
    }   
    /**
     * Método chamar checkin
     * 
     * @param request
     * @param response
     * @return 
     */
    private String chamadaCheckin(HttpServletRequest request, HttpServletResponse response){
        String codigoCerveja = request.getParameter("codigo");
        
        CervejaDao cervejaDao = new CervejaDao();
        Cerveja cerveja =  cervejaDao.getCerveja(Integer.valueOf(codigoCerveja));
        
        request.setAttribute("cerveja", cerveja);
        return "checkin.jsp";
    }
    
    /**
     * Método para efetuar Checkin
     * 
     * @param request
     * @param response
     * @return 
     */
    private String chamadaSalvarCheckin(HttpServletRequest request, HttpServletResponse response,Usuario usuario){
        //String codigoUsuario = request.getParameter("codigoUsuario");
        String codigoCerveja = request.getParameter("codigoCerveja");
        String nota = request.getParameter("nota");
        String comentario = request.getParameter("comentario");
        String valor = request.getParameter("valor");
        
        
        CervejaDao cervejaDao = new CervejaDao();
        Cerveja cerveja =  cervejaDao.getCerveja(Integer.valueOf(codigoCerveja));
        
        CheckIn checkIn = new CheckIn();
        
        checkIn.setCodigoCerveja(Integer.valueOf(codigoCerveja));
        checkIn.setCodigoUsuario(usuario.getCodigo());
        checkIn.setCometario(comentario);
        checkIn.setData(new java.util.Date());        
        checkIn.setNota(Integer.valueOf(nota));
        checkIn.setValor(Double.valueOf(valor));
        
        ControleCheckIn controleCheckIn = new ControleCheckIn();
        try {
            controleCheckIn.grava(checkIn);
            request.setAttribute("efetuado", true);
            request.setAttribute("mensagem", "Checkin na Cerveja "+cerveja.getNome()+" efetuado com sucesso !!!");
        } catch (Exception ex) {
            Logger.getLogger(ControleServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("erro", ex.getMessage());
            request.setAttribute("cerveja",cerveja);
            request.setAttribute("efetuado", false);
            return "/checkin.jsp";
        }
        return chamadaListaCheckin(request, response, usuario);
    }
    
    
    private String chamadaListaCheckin(HttpServletRequest request, HttpServletResponse response,Usuario usuario){
        
        CheckInDao checkInDao = new CheckInDao();
        try {
            ArrayList<CheckIn> listaCheckIn = checkInDao.getAll(usuario.getCodigo());
            request.setAttribute("lista", listaCheckIn);            
        } catch (Exception ex) {
            Logger.getLogger(ControleServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("erro", ex.getMessage());
        }
        return "listacheckin.jsp";
    }
    
    /**
     * Método para chamar consultar Cerveja para checkin
     * 
     * @param request
     * @param response
     * @return 
     */
    private String chamadaOrigemCheckin(HttpServletRequest request, HttpServletResponse response){
        String consulta = request.getParameter("consulta");
        
        ControleCerveja controleCerveja = new ControleCerveja();
        try {
            ArrayList<Cerveja> listaCerveja = controleCerveja.consultarCerveja(consulta);
            request.setAttribute("lista", listaCerveja);            
        } catch (Exception ex) {
            Logger.getLogger(ControleServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("erro", ex.getMessage());
        }
        request.setAttribute("origem", 2);
        return "/consultacerveja.jsp";
    }
    
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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

        processRequest(request, response);
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
        processRequest(request, response);
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

