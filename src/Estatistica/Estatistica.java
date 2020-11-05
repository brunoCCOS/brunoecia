package Estatistica;

import java.util.*;

import Medicao.Medicao;

import java.lang.String;
import java.time.LocalDate;

public abstract class Estatistica{
    protected String nome;
    protected ArrayList<Medicao> obs;
    public abstract void inclui(Medicao a);
    public abstract LocalDate dataInicio();
    public abstract LocalDate dataFim();
    public abstract float valor();
}
