package Distribuicoes;

import java.util.List;

public interface Distribuicao {

	public double getVal();

	public void setParams(Double ... params);

	public List<String> getParams();
}
