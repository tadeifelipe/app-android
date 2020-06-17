package com.example.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.example.agenda.ui.activity.ContantesActivities.CHAVE_ALUNO;


public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APP_BAR = "Lista de Alunos";
    private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APP_BAR);

        configurarFabNovoAluno();
        dao.salva(new Aluno("Felipe","1112222333","felipe@gmail.com"));
    }

    private void configurarFabNovoAluno() {
        FloatingActionButton botao = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirFormularioModoInsereAluno();
            }
        });
    }

    private void abrirFormularioModoInsereAluno() {
        startActivity(new Intent(this,
                FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configurarLista();
    }

    private void configurarLista() {
        ListView lista = findViewById(R.id.activity_lista_de_alunos_listview);
        final List<Aluno> alunos = dao.todos();
        configuraAdapter(lista, alunos);

        configuraListenerdeCliquePorItem(lista);
    }

    private void configuraListenerdeCliquePorItem(ListView lista) {
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoEscolhido = (Aluno) parent.getItemAtPosition(position);
                abreFormularioModoEditaAluno(alunoEscolhido);
            }
        });
    }

    private void abreFormularioModoEditaAluno(Aluno alunoEscolhido) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, alunoEscolhido);
        startActivity(vaiParaFormularioActivity);
    }

    private void configuraAdapter(ListView lista, List<Aluno> alunos) {
        lista.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                alunos));
    }
}
