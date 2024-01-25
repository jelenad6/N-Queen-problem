import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueensGUI extends JFrame implements ActionListener {
    private int N;
    private JButton solveButton;
    private JTextField inputField;
    private JPanel chessboardPanel;
    private JLabel numSolutionsLabel;
    private JLabel label2;

    private NQueensSolver solver; // deklarisanje instance NQueensSolver-a

    public NQueensGUI() {
        setTitle("N-Queens Problem Solver");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Kreiranje komponenti korisnickog interfejsa
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        inputField = new JTextField(10);
        inputPanel.add(new JLabel("Uneti N: "));
        inputPanel.add(inputField);

        solveButton = new JButton("Resi");
        solveButton.addActionListener(this);
        inputPanel.add(solveButton);

        mainPanel.add(inputPanel);

        chessboardPanel = new JPanel(); // inicijalno prazno
        mainPanel.add(chessboardPanel);

        numSolutionsLabel = new JLabel();
        mainPanel.add(numSolutionsLabel);
        
       label2 = new JLabel();
        mainPanel.add(label2);

        setContentPane(mainPanel);
        setVisible(true);
    
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == solveButton) {
            try {
                N = Integer.parseInt(inputField.getText().trim());
                if (N < 4) {
                    JOptionPane.showMessageDialog(this, "Pogresan unos. Uneti ceo broj veci od 4 za pokretanje.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                else {
                    solver = new NQueensSolver(N);
                    List<List<String>> solutions = solver.solveNQueens();
                    updateChessboardPanel(solutions);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Pogresan unos. Uneti ceo broj veci od 4 za pokretanje.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateChessboardPanel(List<List<String>> solutions) {
        chessboardPanel.removeAll();

        if (N >= 9) {
        	// Prikazati broj resenja
            label2.setText("Uneti broj je preveliki da bi se prikazala sahovska tabla.");
            label2.setHorizontalAlignment(JLabel.CENTER);

            numSolutionsLabel.setText("Broj resenja = " + solutions.size());
            numSolutionsLabel.setHorizontalAlignment(JLabel.CENTER);

            JPanel parentPanel = new JPanel(new BorderLayout());

            // Panel za 2 labele za resenja
            JPanel labelPanel = new JPanel(new GridLayout(2, 1)); 

            labelPanel.add(label2);
            labelPanel.add(numSolutionsLabel);

            parentPanel.add(labelPanel, BorderLayout.CENTER);

            getContentPane().add(parentPanel, BorderLayout.CENTER);
        } else if (N >= 4) {
            JPanel parentPanel = new JPanel(new BorderLayout());

           
            JPanel labelPanel = new JPanel(new BorderLayout());

         // Prikazati broj resenja
            numSolutionsLabel.setText("Broj resenja = " + solutions.size());
            numSolutionsLabel.setHorizontalAlignment(JLabel.CENTER);
            labelPanel.add(numSolutionsLabel, BorderLayout.NORTH);

            // Dodati prazan prostor izmedju labele i sahovske table
            JPanel spacePanel = new JPanel();
            spacePanel.setPreferredSize(new Dimension(0, 20)); 
            labelPanel.add(spacePanel, BorderLayout.CENTER);

            parentPanel.add(labelPanel, BorderLayout.NORTH);

            // za sahovsku tablu
            chessboardPanel.setLayout(new GridLayout(N, N));

            for (List<String> solution : solutions) {
                JPanel solutionPanel = new JPanel(new GridLayout(N, N));
                for (String row : solution) {
                    for (char c : row.toCharArray()) {
                        JLabel label = new JLabel(Character.toString(c));
                        label.setHorizontalAlignment(JLabel.CENTER);
                        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        solutionPanel.add(label);
                    }
                }
                chessboardPanel.add(solutionPanel);
            }

            parentPanel.add(chessboardPanel, BorderLayout.CENTER);
            getContentPane().add(parentPanel, BorderLayout.CENTER);
        } else {
            chessboardPanel.setLayout(null); 
            chessboardPanel.setPreferredSize(new Dimension(0, 0)); 
            numSolutionsLabel.setText(""); 
        }

        revalidate();
        repaint();
    }


}