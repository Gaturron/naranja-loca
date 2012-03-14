import java.security.PublicKey;
import java.util.*;
import java.math.*;

public class matrix {
	
	int dim;
	Float matrix[][];
	
	public matrix(int dim){
		this.dim = dim;
		this.matrix = new Float[dim][dim];
		
		for(int i = 1; i <= dim; i++) {
			for(int j = 1; j <= dim; j++) {
				setItem(i, j, Float.POSITIVE_INFINITY);
			}
		}
	}
	
	public void setItem(int rows, int cols, Float item){
		assert dim > rows && dim > cols;
		matrix[rows - 1][cols - 1] = item;
	}
	
	public void setItem(int rows, int cols, int item){
		assert dim > rows && dim > cols;
		matrix[rows - 1][cols - 1] = new Float(item);
	}
	
	public Float getItem(int rows, int cols){
		assert dim > rows && dim > cols;
		return matrix[rows - 1][cols - 1];
	}
	
	public void copy(matrix m){
		assert dim == m.dim;
		
		for(int i = 1; i <= dim; i++) {
			for(int j = 1; j <= dim; j++) {
				setItem(i, j, m.getItem(i, j));
			}
		}
	}
	
	public String toString(){
		String output = "";
		for(int i = 1; i <= dim; i++) {
			output+= " [";
			for(int j = 1; j < dim; j++) 
				output+= tinyNumber(getItem(i, j)) + ", ";
			output+= tinyNumber(getItem(i, dim));
			output+="] ";
			output+= "\n";	
		}
		return output;
	}
	
	private String tinyNumber(Float a){
		if (a == Float.POSITIVE_INFINITY) {
			return "Inf";
		}else{
			return a.toString();
		}
	}
	
	public boolean equals(matrix m){
		assert dim == m.dim;
		
		boolean res = true;		
		for(int i = 1; i <= dim; i++) {
			for(int j = 1; j <= dim; j++) {
				res = (getItem(i, j) == m.getItem(i, j)) && res;
			}
		}
		return res;	
	}
	
	//====================================================================================================//
	
	private int compemento(int n){
		if(n % 2 == 1) { return n+1; }
		return n-1;
	}
	
	private Float min(Float a, Float b, Float c, Float d, Float e){
		Float res = Math.min(a, b);
		res = Math.min(res, c);
		res = Math.min(res, d);
		res = Math.min(res, e);
		return res;
	} 
	
	public void closure(){
		int n = new Double(dim / 2).intValue();
		
		for(int k = 1; k <= n; k++){
			for(int i = 1; i <= 2 * n; i++){
				for(int j = 1; j <= 2 * n; j++){
					Float value = min(	getItem(i, j),
										getItem(i, 2*k - 1) + getItem(2*k - 1, j),
										getItem(i, 2*k)     + getItem(2*k, j),
										getItem(i, 2*k - 1) + getItem(2*k - 1, 2*k) + getItem(2*k, j),
										getItem(i, 2*k )    + getItem(2*k, 2*k - 1) + getItem(2*k - 1, j)
									 );
					setItem(i, j, value);
				}
			}			
		}
		
		for(int i = 1; i <= 2 * n; i++){
			for(int j = 1; j <= 2 * n; j++){
				
				int ic = compemento(i);
				int jc = compemento(j);
				
				Float value = Math.min(getItem(i, j), (getItem(i, ic) + getItem(jc, j)) / 2 );
				setItem(i, j, value);
			}	
		}
		
		for(int i = 1; i <= 2 * n; i++){
			if(getItem(i, i) < 0) {
				
				//devolvemos matriz 0
				matrix res = new matrix(dim);
				for(int ri = 1; ri <= dim; ri++) {
					for(int rj = 1; rj <= dim; rj++) {
						res.setItem(ri, rj, 0);
					}
				}
				copy(res);				
			}else{
				setItem(i, i, 0);
			}
		}
	}
	
	
	
	//====================================================================================================//
	
	public static void main(String[] args){
		//ejemplo
		matrix m = new matrix(4);
		m.setItem(1, 1, 1);
		m.setItem(2, 2, 2);
		System.out.println(m.toString());
		
		matrix n = new matrix(4);
		n.copy(m);
		n.setItem(4, 4, 5);
		System.out.println(n);
		
		System.out.println(m.equals(n));
	}
}
