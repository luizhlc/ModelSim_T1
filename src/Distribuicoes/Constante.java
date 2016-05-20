package Distribuicoes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constante implements Distribuicao{

	private double a;

	public Constante(){

	}

	public Constante(double val){
		a = val;
	}

	@Override
	public double getVal() {
		return a;
	}

	@Override
	public void setParams(Double... params) {
		a = params[0];
	}

	@Override
	public List<String> getParams() {
		String[] params = {"a",};
		return new ArrayList<String>(Arrays.asList(params));
	}
}
