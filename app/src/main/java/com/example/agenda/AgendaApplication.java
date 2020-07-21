package com.example.agenda;

import android.app.Application;

import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AlunoDAO dao = new AlunoDAO();
        dao.salva(new Aluno("Felipe", "1112222333", "felipe@gmail.com"));
        dao.salva(new Aluno("Luísa", "1112222333", "luisa@gmail.com"));
    }
}
