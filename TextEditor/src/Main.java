import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class Main extends JFrame implements ActionListener{
    JFrame frame;
    JTextArea text;
    Main(){
        frame = new JFrame("Note pad +");
        text = new JTextArea();
        frame.setSize(500, 500);
        frame.setBackground(Color.lightGray);
        frame.setVisible(true);
        frame.add(text);
        // add text
        JMenuBar menubar = new JMenuBar();
        // creation a Jmenu
        JMenu Filemenu = new JMenu("File");
        JMenu Editormenu = new JMenu("Editor");
        menubar.add(Filemenu);
        menubar.add(Editormenu);
        // creat a file menu and added menu
        JMenuItem Open = new JMenuItem("Open");
        JMenuItem Save = new JMenuItem("Save File");
        JMenuItem Print = new JMenuItem("Print");
        JMenuItem newFile = new JMenuItem("New File");
        Save.addActionListener(this);
        Open.addActionListener(this);
        Print.addActionListener(this);
        newFile.addActionListener(this);
        Filemenu.add(Open);
        Filemenu.add(Save);
        Filemenu.add(Print);
        Filemenu.add(newFile);
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");
        Editormenu.add(cut);
        Editormenu.add(copy);
        Editormenu.add(paste);
        // addition listener
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        // added the menu bar
        frame.setJMenuBar(menubar);
        frame.show();
        //For closing the source
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        Main simple= new Main();
    }
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s == "cut") {
            text.cut();
        } else if (s == "copy") {
            text.copy();
        } else if (s == "paste") {
            text.paste();
        } else if (s == "save file") {
            JFileChooser jfilechoose = new JFileChooser("C:");
            int ans = jfilechoose.showOpenDialog(null);
            if (ans == JFileChooser.APPROVE_OPTION) {
                File file = new File(jfilechoose.getSelectedFile().getAbsolutePath());
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter(file, false));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try { //write a character to the internal buffer
                    writer.write(text.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try { // this method forces the writer to write all data present in the buffer to the destination file
                    writer.flush();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else if (s == "print") {
            try {
                text.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        } else if (s == "open") {
            JFileChooser Jfilechoose = new JFileChooser("C:");
            int ans = Jfilechoose.showOpenDialog(null);
            if (ans == JFileChooser.APPROVE_OPTION) {
                File file = new File(Jfilechoose.getSelectedFile().getAbsolutePath());
                try {
                    String s1 = "";
                    String s2 = "";
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    s2 = bufferedReader.readLine();
                    while ((s1 = bufferedReader.readLine()) != null) {
                        s2 += s1 + "\n";
                    }
                    text.setText(s2);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }
        else if(s == "new file"){
            text.setText(" ");
        }
    }
}
