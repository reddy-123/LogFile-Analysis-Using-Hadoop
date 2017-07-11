packagenet.codejava.upload;
import java.io.*;
import java.net.URL;
importjava.net.URLConnection;
importjava.net.URLEncoder;
importjava.sql.*;
importjava.util.Enumeration;
importjava.util.Iterator;
importjava.util.List;
importjavax.servlet.*;
importjavax.servlet.http.*;
importorg.apache.commons.fileupload.FileItem;
importorg.apache.commons.fileupload.FileItemFactory;
importorg.apache.commons.fileupload.FileUploadException;
importorg.apache.commons.fileupload.disk.DiskFileItemFactory;
importorg.apache.commons.fileupload.servlet.ServletFileUpload;
 Servlet implementation class getLogin
public class UploadFile extends HttpServlet {
private static final long serialVersionUID = 17864986468494864L;
    // location to store file uploaded
private static final String UPLOAD_DIRECTORY = "upload";
    // upload settings
publicUploadFile() {
super();
        // TODO Auto-generated constructor stub
    }
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //doPost(request, response);
        //throw new ServletException("GET method used with " +  getClass( ).getName( )+": POST method required.");
request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
System.out.println("demo");
if (!ServletFileUpload.isMultipartContent(request)) {
            // if not, we stop here
PrintWriter writer = response.getWriter();
writer.println("Error: Form must has enctype=multipart/form-data.");
writer.flush();
return;
        }
        // configures upload settings
DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets temporary location to store files
factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
ServletFileUpload upload = new ServletFileUpload(factory);
        // constructs the directory path to store upload file
        // this path is relative to application's directory
   //     String uploadPath = getServletContext().getRealPath("")+ File.separator + UPLOAD_DIRECTORY;
        String uploadPath = "C:/hadoop-2.3.0/hadoop2-dir/datanode-dir"+ File.separator + UPLOAD_DIRECTORY;
        // creates the directory if it does not exist
        File uploadDir = new File(uploadPath);
if (!uploadDir.exists()) {
uploadDir.mkdir();
        }
try {
            // parses the request's content to extract file data

System.out.println(uploadPath);
            List<FileItem>formItems = upload.parseRequest((HttpServletRequest)request);
if (formItems != null &&formItems.size() > 0) {
                // iterates over form's fields
for (FileItem item : formItems) {
                    // processes only fields that are not form fields
if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        // C:\tomcat\apache-tomcat-7.0.40\webapps\data\
                        // saves the file on disk
item.write(storeFile);
request.setAttribute("message","Upload has been done successfully!");
System.out.println("SUCCESSFULLY UPLOADED");
                    }
                }
            }
        } catch (Exception ex) {
request.setAttribute("message","There was an error: " + ex.getMessage());
System.out.println("demo Fail: " +   ex.getMessage() );
        }
    }
}
