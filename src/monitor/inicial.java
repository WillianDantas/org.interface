package monitor;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;



public class inicial {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		JTextArea texto = new JTextArea();
		
		JFrame janela = new JFrame("Meu primeiro frame em Java");
		janela.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        janela.setSize(1000,700);
        janela.setVisible(true);
        janela.getContentPane().setBackground(Color.WHITE);
        
        texto.setSize(900, 900);
        texto.setEditable(false);
        texto.setBackground(Color.WHITE);
        texto.setLineWrap(true);
     
        
        
        JMenuBar menuBar = new JMenuBar();
        janela.setJMenuBar(menuBar);
        
        JMenu menu = new JMenu("Arquivo");
        
       
        JMenuItem menuItem = new JMenuItem("Sair");
        JMenuItem menuItemIniciar =  new JMenuItem("Iniciar");
        
        Font font = new Font("arial", Font.PLAIN, 10);
        menu.setFont(font);
        menuItem.setFont(font);
        menuItemIniciar.setFont(font);
        texto.setFont(font);
        
        
        
        texto.append("teste \n");
        
        janela.add(texto);
        

        texto.append("foi \n");
        
        janela.add(texto);
        
        
        
        menuItem.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent actionEvent) {
            	int close = JOptionPane.showConfirmDialog(null,
            			"Deseja sair do sistema?",
            			"Question (modal dialog)",    
            			JOptionPane.YES_NO_OPTION,            			
            			JOptionPane.QUESTION_MESSAGE);
            	
            	 if (close < 1) {
            		 janela.dispose();
            	 }
            }
        });
        
        menuItemIniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent)  {
				try {
					
				    pegaHtml(texto);
				    lerXml(texto);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
        
        menu.add(menuItemIniciar);
        menu.add(menuItem);
        
        
        menuBar.add(menu);

	}
	
	
	public static void  pegaHtml(JTextArea texto) throws IOException {
		new Thread() {
			public void run() {
				String url = "";
				String imgSrc = "";
				String lerJson = "";
				 try {
					    
						url = "http://www.androidbegin.com";
						Document document = Jsoup.connect(url).get();
						url = "https://www.btgpactualdigital.com/services/public/funds/2504";
						lerJson = Jsoup.connect(url).validateTLSCertificates(false).ignoreContentType(true).get().body().text();
						Elements img = document.select("a[title=AndroidBegin] img[src]");
						imgSrc = img.attr("src");
					 }catch (IOException e) {
						e.printStackTrace();
					}
					 
					 texto.append(imgSrc + "\n");
			}
		}.start();

	}
	
	
	public static void lerXml(JTextArea texto) {
		new Thread() {
			public void run() {
				xmlDoc doc = null;
				try {
					String dir = System.getProperty("user.dir");		    
				    /*JAXBContext jaxbContext = JAXBContext.newInstance(xmlDoc.class);
				    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				    Customer customer = (xmlDoc) jaxbUnmarshaller.unmarshal(new File(dir  + "\\xml\\configuracao.xml"));*/
					
					XStream xstream = new XStream(new DomDriver()); 
					xstream.alias("xmlDoc", xmlDoc.class);
					xstream.processAnnotations(xmlDoc.class);
					BufferedReader input = new BufferedReader(new FileReader(dir  + "\\xml\\configuracao.xml"));

					doc = (xmlDoc) xstream.fromXML(input);
					texto.append(Integer.toString(doc.getAge()) + "\n");
					texto.append(doc.getName() + "\n" );
				    
				} catch (Exception e) {
					e.printStackTrace();

				}
				
			}
			
			
		}.start();
		
		
		
	}

}
