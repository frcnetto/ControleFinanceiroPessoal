package br.estacio.poo.cfp.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import br.estacio.poo.cfp.dao.CidadeDao;
import br.estacio.poo.cfp.dao.EstadoDao;
import br.estacio.poo.cfp.dao.FornecedorDao;
import br.estacio.poo.cfp.entidades.Fornecedor;
import br.estacio.poo.cfp.util.FornecedorTableModel;
import br.estacio.poo.cfp.util.Imagens;
import br.estacio.poo.cfp.util.TrataComboBox;
import br.estacio.poo.cfp.util.ValidaCampos;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class BuscFornecedor extends JInternalFrame implements ItemListener, KeyListener, ActionListener, MouseListener{

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
    private JButton btnCancelar;
    private JButton btnRetornar;
    private JTable tbResult;
    private JScrollPane scpResult;
	private FornecedorTableModel tbResultModel;
	private JButton btnConsultar;
	private FornecedorDao fornecedorDao;
	private CadPagamento framePaiCPagamento;
	private BuscPagamento framePaiBPagamento;
	private JDesktopPane dsktPane;
	private JButton btnExcluirSelecionado;

	public BuscFornecedor() {
		getContentPane().setLayout(null);
		
		imagens = new Imagens();
		fornecedorDao = new FornecedorDao();
		
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
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(imagens.getImagens(1));
		btnCancelar.setBounds(425, 425, 95, 25);
		getContentPane().add(btnCancelar);
		
		btnRetornar = new JButton("Ok");
		btnRetornar.setIcon(imagens.getImagens(12));
		btnRetornar.setBounds(320, 425, 95, 25);
		btnRetornar.addActionListener(this);
		getContentPane().add(btnRetornar);
		
		scpResult = new JScrollPane();
		scpResult.setBounds(10, 101, 510, 313);
		getContentPane().add(scpResult);
		
		tbResult = new JTable();
		tbResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbResultModel = new FornecedorTableModel();
		tbResult.setModel(tbResultModel);
		tbResult.addMouseListener(this);
		scpResult.setViewportView(tbResult);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setIcon(imagens.getImagens(7));
		btnConsultar.addActionListener(this);
		btnConsultar.setBounds(419, 69, 101, 25);
		getContentPane().add(btnConsultar);	
		
		btnExcluirSelecionado = new JButton("Excluir selecionado");
		btnExcluirSelecionado.addActionListener(this);
		btnExcluirSelecionado.setBounds(189, 426, 123, 23);
		getContentPane().add(btnExcluirSelecionado);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Consulta de Fornecedores");
		setSize(546, 488);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        setFrameIcon(imagens.getImagens(0));
        setVisible(true);
	}
	
	public BuscFornecedor(JDesktopPane dsktPane) {
		getContentPane().setLayout(null);
		
		imagens = new Imagens();
		fornecedorDao = new FornecedorDao();
		this.dsktPane = dsktPane; 
		
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
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(imagens.getImagens(1));
		btnCancelar.setBounds(425, 425, 95, 25);
		getContentPane().add(btnCancelar);
		
		btnRetornar = new JButton("Ok");
		btnRetornar.setIcon(imagens.getImagens(12));
		btnRetornar.setBounds(320, 425, 95, 25);
		btnRetornar.addActionListener(this);
		getContentPane().add(btnRetornar);
		
		scpResult = new JScrollPane();
		scpResult.setBounds(10, 101, 510, 313);
		getContentPane().add(scpResult);
		
		tbResult = new JTable();
		tbResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbResultModel = new FornecedorTableModel();
		tbResult.setModel(tbResultModel);
		tbResult.addMouseListener(this);
		scpResult.setViewportView(tbResult);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setIcon(imagens.getImagens(7));
		btnConsultar.addActionListener(this);
		btnConsultar.setBounds(419, 69, 101, 25);
		getContentPane().add(btnConsultar);	
		
		btnExcluirSelecionado = new JButton("Excluir selecionado");
		btnExcluirSelecionado.addActionListener(this);
		btnExcluirSelecionado.setBounds(189, 426, 123, 23);
		getContentPane().add(btnExcluirSelecionado);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Consulta de Fornecedores");
		setSize(546, 488);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        setFrameIcon(imagens.getImagens(0));
        setVisible(true);
	}
	
	public BuscFornecedor(final CadPagamento framePai) {
		getContentPane().setLayout(null);
		
		this.framePaiCPagamento = framePai;
		
		imagens = new Imagens();
		fornecedorDao = new FornecedorDao();
		
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
		cmbxCidade.setEnabled(false);
		getContentPane().add(cmbxCidade);
		
		cmbxUF = new JComboBox<String>();
		cmbxUF.setBounds(10, 70, 146, 20);
		ufModel = manipulaCombo.carregaCombo(cmbxUF, estadoDao.carregaUf());
        cmbxUF.setModel(ufModel);
		cmbxUF.addItemListener(this);
		getContentPane().add(cmbxUF);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(imagens.getImagens(1));
		btnCancelar.setBounds(425, 425, 95, 25);
		getContentPane().add(btnCancelar);
		
		btnRetornar = new JButton("Ok");
		btnRetornar.setIcon(imagens.getImagens(12));
		btnRetornar.setBounds(320, 425, 95, 25);
		btnRetornar.addActionListener(this);
		getContentPane().add(btnRetornar);
		
		scpResult = new JScrollPane();
		scpResult.setBounds(10, 101, 510, 313);
		getContentPane().add(scpResult);
		
		tbResult = new JTable();
		tbResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbResultModel = new FornecedorTableModel();
		tbResult.setModel(tbResultModel);
		tbResult.addMouseListener(this);
		scpResult.setViewportView(tbResult);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setIcon(imagens.getImagens(7));
		btnConsultar.addActionListener(this);
		btnConsultar.setBounds(419, 69, 101, 25);
		getContentPane().add(btnConsultar);	
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Consulta de Fornecedores");
		setSize(546, 488);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        setFrameIcon(imagens.getImagens(0));
        setVisible(true);
        toFront();
	}
	
	public BuscFornecedor(final BuscPagamento framePai) {
		getContentPane().setLayout(null);
		
		this.framePaiBPagamento = framePai;
		
		imagens = new Imagens();
		fornecedorDao = new FornecedorDao();
		
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
		cmbxCidade.setEnabled(false);
		getContentPane().add(cmbxCidade);
		
		cmbxUF = new JComboBox<String>();
		cmbxUF.setBounds(10, 70, 146, 20);
		ufModel = manipulaCombo.carregaCombo(cmbxUF, estadoDao.carregaUf());
        cmbxUF.setModel(ufModel);
		cmbxUF.addItemListener(this);
		getContentPane().add(cmbxUF);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(imagens.getImagens(1));
		btnCancelar.setBounds(425, 425, 95, 25);
		getContentPane().add(btnCancelar);
		
		btnRetornar = new JButton("Ok");
		btnRetornar.setIcon(imagens.getImagens(12));
		btnRetornar.setBounds(320, 425, 95, 25);
		btnRetornar.addActionListener(this);
		getContentPane().add(btnRetornar);
		
		scpResult = new JScrollPane();
		scpResult.setBounds(10, 101, 510, 313);
		getContentPane().add(scpResult);
		
		tbResult = new JTable();
		tbResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbResultModel = new FornecedorTableModel();
		tbResult.setModel(tbResultModel);
		tbResult.addMouseListener(this);
		scpResult.setViewportView(tbResult);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setIcon(imagens.getImagens(7));
		btnConsultar.addActionListener(this);
		btnConsultar.setBounds(419, 69, 101, 25);
		getContentPane().add(btnConsultar);	
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Consulta de Fornecedores");
		setSize(546, 488);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        setFrameIcon(imagens.getImagens(0));
        setVisible(true);
        toFront();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnRetornar){
			if(framePaiCPagamento != null || framePaiBPagamento != null){
				if(framePaiCPagamento != null){
					framePaiCPagamento.setFornecedor(tbResultModel.getLinha(tbResult.getSelectedRow()));
					framePaiCPagamento.setNome(framePaiCPagamento.getFornecedor().getNome());
					this.dispose();
				} else if(framePaiBPagamento != null){
					framePaiBPagamento.setFornecedor(tbResultModel.getLinha(tbResult.getSelectedRow()));
					framePaiBPagamento.setNome(framePaiBPagamento.getFornecedor().getNome());
					this.dispose();
				}
			} else{
				this.dispose();
			}
		} else if(e.getSource() == btnConsultar){
			if(cmbxUF.getSelectedIndex() == 0){
				tbResultModel.limpaModel();
				fornecedorDao.todosComNome(nome.getText(), tbResultModel);
			} else if(cmbxCidade.getSelectedIndex() == 0){
				tbResultModel.limpaModel();
				fornecedorDao.todosComNome(nome.getText(), cmbxUF.getSelectedItem().toString(), tbResultModel);
			} else{
				tbResultModel.limpaModel();
				fornecedorDao.todosComNome(nome.getText(), cmbxUF.getSelectedItem().toString(), cmbxCidade.getSelectedItem().toString(), tbResultModel);
			}
		} else if(e.getSource() == btnExcluirSelecionado){
			
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
		if(e.getSource() == cmbxUF){
			if(e.getStateChange() == ItemEvent.SELECTED){
				cidadeModel = manipulaCombo.carregaCombo(
						cmbxCidade, 
						cidadeDao.carregaCidade(
								estadoDao.carregaId(
										cmbxUF.getSelectedItem().toString()
										)
						)
				);
		        cmbxCidade.setModel(cidadeModel);
		        cmbxCidade.setEnabled(true);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == tbResult){
			if(e.getClickCount() >= 2){
				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setCod((int) tbResultModel.getValueAt(tbResult.getSelectedRow(), 0));
				fornecedor.setNome((String) tbResultModel.getValueAt(tbResult.getSelectedRow(), 1));
				fornecedor.setEndereco((String) tbResultModel.getValueAt(tbResult.getSelectedRow(), 2));
				fornecedor.setUf((int) tbResultModel.getValueAt(tbResult.getSelectedRow(), 3));
				fornecedor.setCidade((int) tbResultModel.getValueAt(tbResult.getSelectedRow(), 4));
				fornecedor.setTelefone((String) tbResultModel.getValueAt(tbResult.getSelectedRow(), 5));
				fornecedor.setCelular((String) tbResultModel.getValueAt(tbResult.getSelectedRow(), 6));
				fornecedor.setTipo((String) tbResultModel.getValueAt(tbResult.getSelectedRow(), 7));
				fornecedor.setDesc((String) tbResultModel.getValueAt(tbResult.getSelectedRow(), 8));
				fornecedor.setValorl((float) tbResultModel.getValueAt(tbResult.getSelectedRow(), 9));
				fornecedor.setObs((String) tbResultModel.getValueAt(tbResult.getSelectedRow(), 10));
				if(dsktPane != null){
					dsktPane.add(new CadFornecedor(fornecedor));
				} 
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
