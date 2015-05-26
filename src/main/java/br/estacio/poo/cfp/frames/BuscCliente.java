package br.estacio.poo.cfp.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import br.estacio.poo.cfp.dao.CidadeDao;
import br.estacio.poo.cfp.dao.ClienteDao;
import br.estacio.poo.cfp.dao.EstadoDao;
import br.estacio.poo.cfp.util.Imagens;
import br.estacio.poo.cfp.util.TrataComboBox;
import br.estacio.poo.cfp.util.ValidaCampos;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class BuscCliente extends JInternalFrame implements ItemListener, KeyListener, ActionListener{

	private static final long serialVersionUID = 1L;

    private Imagens imagens;
    private JLabel lblNome;
    private JFormattedTextField nome;
    private ValidaCampos valida = new ValidaCampos();
    private JComboBox<String> cmbxUF;
    private EstadoDao estadoDao = new EstadoDao();
    private ComboBoxModel<String> ufModel;
    private ComboBoxModel<String> cidadeModel;
    private TrataComboBox manipulaCombo = new TrataComboBox();
    private JLabel lblUf;
    private JLabel lblCidade;
    private JComboBox<String> cmbxCidade;
    private CidadeDao cidadeDao = new CidadeDao();
    private JCheckBox contrato;
    private JButton btnCancelar;
    private JButton btnOk;
    private JTable tbResult;
    private DefaultTableModel tbResutModel;
    private JScrollPane scpResult;
    private JButton btnConsultar;
    private ClienteDao clienteDao;

	public BuscCliente() {
		getContentPane().setLayout(null);
		
		imagens = new Imagens();
		clienteDao = new ClienteDao();
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 11, 27, 14);
		getContentPane().add(lblNome);
		
		nome = new JFormattedTextField();
		nome.setBounds(10, 27, 510, 20);
		nome.addKeyListener(this);
		getContentPane().add(nome);
		nome.setColumns(10);
		
		lblUf = new JLabel("UF");
		lblUf.setBounds(10, 54, 13, 14);
		getContentPane().add(lblUf);
		
		lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(166, 54, 33, 14);
		getContentPane().add(lblCidade);
		
		cmbxCidade = new JComboBox<String>();
		cmbxCidade.setBounds(166, 70, 146, 20);
		cidadeModel = manipulaCombo.carregaCombo(cmbxCidade, cidadeDao.carregaCidade());
		cmbxCidade.setModel(cidadeModel);
		getContentPane().add(cmbxCidade);
		
		cmbxUF = new JComboBox<String>();
		cmbxUF.setBounds(10, 70, 146, 20);
		ufModel = manipulaCombo.carregaCombo(cmbxUF, estadoDao.carregaUf());
        cmbxUF.setModel(ufModel);
		cmbxUF.addItemListener(this);
		getContentPane().add(cmbxUF);
		
		contrato = new JCheckBox("Contrato?");
		contrato.setBounds(318, 69, 73, 23);
		getContentPane().add(contrato);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(imagens.getImagens(1));
		btnCancelar.setBounds(425, 425, 95, 25);
		getContentPane().add(btnCancelar);
		
		btnOk = new JButton("Ok");
		btnOk.setIcon(imagens.getImagens(12));
		btnOk.setBounds(320, 425, 95, 25);
		btnOk.addActionListener(this);
		getContentPane().add(btnOk);
		
		scpResult = new JScrollPane();
		scpResult.setBounds(10, 101, 510, 313);
		getContentPane().add(scpResult);
		
		tbResutModel = new DefaultTableModel();
		
		tbResult = new JTable();
		tbResult.setModel(tbResutModel);
		scpResult.setViewportView(tbResult);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(this);
		btnConsultar.setIcon(imagens.getImagens(7));
		btnConsultar.setBounds(419, 69, 101, 25);
		getContentPane().add(btnConsultar);
		
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Consulta de Clientes");
		setSize(546, 488);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        setFrameIcon(imagens.getImagens(0));
        setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnOk){
			
		}else if(e.getSource() == btnConsultar){
			clienteDao.buscaPeloNome(nome.getText());
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getSource() == nome){
			valida.validaSomenteCom(valida.LETRAS + valida.ACENTOS + valida.ESPACO, e);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
	}
}
