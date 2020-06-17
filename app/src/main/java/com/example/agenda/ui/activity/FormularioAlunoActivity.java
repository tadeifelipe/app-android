package com.example.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;

import static com.example.agenda.ui.activity.ContantesActivities.CHAVE_ALUNO;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APP_EDITA_BAR = "Edita aluno";
    private static final String TITULO_APP_NOVO_BAR = "Novo Aluno" ;
    private TextView campoNome;
    private TextView campoTelefone;
    private TextView campoEmail;
    private final AlunoDAO dao = new AlunoDAO();
    private Aluno aluno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        iniciarCampos();
        configurarBotao();
        carregaAluno();
    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_ALUNO)){
            setTitle(TITULO_APP_EDITA_BAR);
            aluno = (Aluno)dados.getSerializableExtra(CHAVE_ALUNO);
            preencheCampos();
        } else {
            setTitle(TITULO_APP_NOVO_BAR);
            aluno = new Aluno();
        }
    }

    private void preencheCampos() {
        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        campoEmail.setText(aluno.getEmail());
    }

    private void configurarBotao() {
        Button botao = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalizaFormulario();
            }
        });
    }

    private void finalizaFormulario() {
        preencheAluno();
        if (aluno.temIdValido()){
            dao.edita(aluno);
        } else {
            dao.salva(aluno);
        }
        finish();
    }

    private void iniciarCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void salvarAluno(Aluno aluno) {
        dao.salva(aluno);
        finish();
    }

    private void preencheAluno() {
       aluno.setNome(campoNome.getText().toString());
       aluno.setTelefone(campoTelefone.getText().toString());
       aluno.setEmail(campoEmail.getText().toString());
    }
}
