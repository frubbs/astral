
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/ResultadoPortaAstral"})
public class ResultNovoPortalAstral extends HttpServlet {

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
        
        
        Previsao prev = gerarPrevisao(request);
        
                
        request.setAttribute("previsao", prev);
        
        request.getRequestDispatcher("resultado.jsp").forward(request, response);
        
        
        
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

    @SuppressWarnings("empty-statement")
    
    private int somaValorLetras(String texto) {
        char[] letras = texto.toCharArray();
        
        int num = 0, soma = 0;
        
        for (int i = 0; i < letras.length; i++) {
            soma = soma + Character.getNumericValue(letras[i]);
        }
        
        if (letras.length > 0) {
           num = verificaDigitos(soma); 
        } 
        return num;
    }
    
    //Verifica quantidade de dígitos de um número e soma até que sobrar 1 dígito
    private int verificaDigitos(int numero){
        boolean fim = false;

        do {
            // Converte resultado em VETOR de caracteres
            char [] digitos;
            digitos = Integer.toString(numero).toCharArray();
            
            // Testa a quantidade de dígitos resultado da soma
            if (digitos.length > 1) {
                int soma = 0;
                
                // Soma valores correspondente aos dígitos resultado
                for (int i = 0; i < digitos.length; i++) {
                    soma = soma + Character.getNumericValue(digitos[i]);       
                }
                numero = soma;
                
            } else {
                fim = true;
            }
        } while (!fim);           
        
        return numero;
    }

    //Verifica SIGNO
    private String verificaSigno(String data){
        String signo = null;
        
        // Define formato para validação da data
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        
        try {
            // Validaação da Data de Nascimento
            Date dData = sdf.parse(data);
            
            // Seleciona o dia da Data de Nascimento
            String sDia = data.substring(0, 2);
            int dia = Integer.parseInt(sDia);
            
            // Seleciona o mês da Data de Nascimento
            String mes = data.substring(3, 5);

            if (signo == null) {
                if ( (mes.equals("01") && dia >= 21) ||
                     (mes.equals("02") && dia <= 19) ) return "Aquário";
                if ( (mes.equals("02") && dia >= 20) ||
                     (mes.equals("03") && dia <= 20) ) return "Peixes";        
                if ( (mes.equals("03") && dia >= 21) ||
                     (mes.equals("04") && dia <= 20) ) return "Áries"; 
                if ( (mes.equals("04") && dia >= 21) ||
                     (mes.equals("05") && dia <= 20) ) return "Touro";         
                if ( (mes.equals("05") && dia >= 21) ||
                     (mes.equals("06") && dia <= 20) ) return "Gémeos";   
                if ( (mes.equals("06") && dia >= 21) ||
                     (mes.equals("07") && dia <= 21) ) return "Câncer";
                if ( (mes.equals("07") && dia >= 22) ||
                     (mes.equals("08") && dia <= 22) ) return "Leão";  
                if ( (mes.equals("08") && dia >= 23) ||
                     (mes.equals("09") && dia <= 22) ) return "Virgem";  
                if ( (mes.equals("09") && dia >= 23) ||
                     (mes.equals("10") && dia <= 22) ) return "Libra";  
                if ( (mes.equals("10") && dia >= 23) ||
                     (mes.equals("11") && dia <= 21) ) return "Escopião";  
                if ( (mes.equals("11") && dia >= 22) ||
                     (mes.equals("12") && dia <= 21) ) return "Sagitário";  
                if ( (mes.equals("12") && dia >= 22) ||
                     (mes.equals("01") && dia <= 20) ) return "Capricórnio";  
            }            
        } catch(ParseException e) {
            signo = "Não foi possível avaliar porque a Data de Nascimento informada é Inválida";
        }
     
        return signo;
    } 

    //Sorteio de Carta 
    private String sorteioCarta(){
        int num;
        String desc = null;
        
        Random gerador = new Random();
        num = gerador.nextInt(5);
        
        switch (gerador.nextInt(5)) {
            case 0:
                desc = "http://rafaelhenrique.net/tarot/tarot1.jpg"; break;
            case 1:
                desc = "http://rafaelhenrique.net/tarot/tarot2.jpg"; break;
            case 2:                
                desc = "http://rafaelhenrique.net/tarot/tarot3.jpg"; break;
            case 3:
                desc = "http://rafaelhenrique.net/tarot/tarot4.jpg"; break;
            case 4:
                desc = "http://rafaelhenrique.net/tarot/tarot5.jpg"; break;
            default:
        }
        return desc;
    }

    //Criptografia ZENIT-POLAR
    private String criptografaTexto(String texto){
        char[] letras = texto.toCharArray();
        
        char iTextoConvertido [] = new char [letras.length];

        for (int i = 0; i < letras.length; i++) {
            switch (letras[i]) {
                case 'P' :
                    iTextoConvertido[i] = 'Z'; break;
                case 'O' :
                    iTextoConvertido[i] = 'E'; break;
                case 'L' :
                    iTextoConvertido[i] = 'N'; break;
                case 'A' :
                    iTextoConvertido[i] = 'I'; break;
                case 'R' :
                    iTextoConvertido[i] = 'T'; break;
                case 'Z' :
                    iTextoConvertido[i] = 'P'; break;
                case 'E' :
                    iTextoConvertido[i] = 'O'; break;
                case 'N' :
                    iTextoConvertido[i] = 'L'; break;
                case 'I' :
                    iTextoConvertido[i] = 'A'; break;
                case 'T' :
                    iTextoConvertido[i] = 'R'; break;                    
                default:
                    iTextoConvertido[i] = letras[i]; 
            }
        }           
        String nome = new String(iTextoConvertido);
        
        return nome;
    }

    private Previsao gerarPrevisao(HttpServletRequest request) {
        // Recebe o nome do formulário
        String sNome = request.getParameter("sNomeForm");
        String sData = request.getParameter("sDtNascForm");
        // Calcula o valor referente as letras que compõe o nome
        int iNumero = somaValorLetras(sNome);
        if (iNumero == 0) sNome = "Nome não informado";
        // Verifica o SIGNO
        String sSigno = verificaSigno(sData);
        // Sorteio de carta
        String  sUrlImagem = sorteioCarta();
        // Criptografa Nome
        String sNomeCriptografado = criptografaTexto(sNome.toUpperCase());
        Previsao prev = new Previsao();
        prev.setNome(sNome);
        prev.setNumero(iNumero);
        prev.setCripto(sNomeCriptografado);
        prev.setData(sData);
        prev.setUrl(sUrlImagem);
        prev.setSigno(sSigno);
        return prev;
    }
}
