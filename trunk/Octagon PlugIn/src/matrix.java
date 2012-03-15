import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

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
			return " Inf";
		}
		if ( a >= 0 && a < 10){
			return " "+a.toString();
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
	
	private Float forget(int f, int i, int j){
		assert f > 0;
		
		if( ( !( i == 2*f - 1 || i == 2*f ) ) && ( !( j == 2*f - 1 || j == 2*f ) ) ){
			return getItem(i, j);
		}
		
		if( ( i == j && j == 2*f - 1) || ( i == j && j == 2*f) ){
			return new Float(0);
		}
		return new Float(Float.POSITIVE_INFINITY);
	}

	public void vjo_assign_e(int jo, int e){
		// Vjo <- e
		assert jo > 0;
		
		matrix closureMatrix = new matrix(dim);
		closureMatrix.copy(this);
		closureMatrix.closure();
		
		for(int i = 1; i <= dim; i++) {
			for(int j = 1; j <= dim; j++) {
				
				Float value = closureMatrix.forget(jo, i, j);
				if( i == 2*jo -1 && j == 2*jo    ){ value = new Float(-2*e); }
				if( i == 2*jo    && j == 2*jo - 1){ value = new Float( 2*e); }
				
				setItem(i, j, value);				
			}
		}		
	}         
	
	public void vjo_assign_vjo_P_e(int jo, int e){
		//Vjo <- Vjo + e
	    assert jo > 0;
	    
	    for(int i = 1; i <= dim; i++) {
			for(int j = 1; j <= dim; j++) {
				
				Float value = getItem(i, j);
				if( ( i == 2*jo - 1 && !( j == 2*jo - 1 || j == 2*jo) ) || ( j == 2*jo && !( i == 2*jo - 1 || i == 2*jo) ) ) { value = getItem(i, j) - new Float(  e); }
				if( ( j == 2*jo - 1 && !( i == 2*jo - 1 || i == 2*jo) ) || ( i == 2*jo && !( j == 2*jo - 1 || j == 2*jo) ) ) { value = getItem(i, j) + new Float(  e); }
				if( i == 2*jo - 1 && j == 2*jo    ){ value = getItem(i, j) - new Float(2*e); }
				if( i == 2*jo     && j == 2*jo - 1){ value = getItem(i, j) + new Float(2*e); }
				setItem(i, j, value);				
			}
		}
	}

	public void vjo_assign_vio_P_e(int jo, int io, int e){
		//Vjo <- Vio + e
	    assert jo > 0;
	    assert io > 0;
	    assert io != jo;
	    
		matrix closureMatrix = new matrix(dim);
		closureMatrix.copy(this);
		closureMatrix.closure();
		
		for(int i = 1; i <= dim; i++) {
			for(int j = 1; j <= dim; j++) {
				
				Float value = closureMatrix.forget(jo, i, j);
				if( (i == 2*jo - 1 && j == 2*io - 1) || (i == 2*io  && j == 2*jo  ) ){ value = new Float(-e); }
				if( (i == 2*io - 1 && j == 2*jo - 1) || (i == 2*jo  && j == 2*io  ) ){ value = new Float( e); }
				setItem(i, j, value);				
			}
		}
	}
	
	public void vjo_assign_M_vjo(int jo){
		//Vjo <- - Vjo 
	    assert jo > 0;
	    
	    for(int i = 1; i <= dim; i++) {
			for(int j = 1; j <= dim; j++) {
				
				int ic = compemento(i);
				int jc = compemento(j);
								
				Float value = null;
				if(    (i == 2*jo - 1 || i == 2*jo )  && (! (j == 2*jo - 1 || j == 2*jo ) )) { value = getItem(ic, j ); }
				if( ( !(i == 2*jo - 1 || i == 2*jo )) &&    (j == 2*jo - 1 || j == 2*jo )  ) { value = getItem(i , jc); }
				if(    (i == 2*jo - 1 || i == 2*jo )  &&    (j == 2*jo - 1 || j == 2*jo )  ) { value = getItem(ic, jc); }
				if( ( !(i == 2*jo - 1 || i == 2*jo )) && (! (j == 2*jo - 1 || j == 2*jo ) )) { value = getItem(i , j ); }
				setItem(i, j, value);				
			}
		}
	}

	public void vjo_assign_M_vio(int jo, int io, int e){
		//Vjo <- - Vio
	    assert jo > 0;
	    assert io > 0;
	    assert io != jo;
	    
	    vjo_assign_vio_P_e(jo, io, e);
	    vjo_assign_M_vjo(jo);
	}

	public void vjo_assign_M_vjo_P_e(int jo, int e){
		//Vjo <- - vjo + e
		System.err.println("implementame!");
	}
	
	public void vjo_assign_M_vio_P_e(int jo, int io, int e){
		//Vjo <- - vio + e
		System.err.println("implementame!");
	}
	
	//====================================================================================================//

	public void Q_vjo_P_e_leq_0(int jo, int e){
		//vjo + e <= 0
		
		for(int i = 1; i <= dim; i++) {
			for(int j = 1; j <= dim; j++) {
				
				Float value = getItem(i, j);
				if( i == 2*jo && j == 2*jo - 1){ value = Math.min(getItem(i, j), new Float(-2*e)); }
				setItem(i, j, value);				
			}
		}		
	}
	
	public void Q_M_vjo_P_e_leq_0(int jo, int e){
		//- vjo + e <= 0
		
		for(int i = 1; i <= dim; i++) {
			for(int j = 1; j <= dim; j++) {
				
				Float value = getItem(i, j);
				if( i == 2*jo - 1 && j == 2*jo ) { value = Math.min(getItem(i, j), new Float(-2*e)); }
				setItem(i, j, value);				
			}
		}
	}
	
	public void Q_vjo_M_vio_P_e_leq_0(int jo, int io, int e){
		//vjo - vio + e <= 0
		
		for(int i = 1; i <= dim; i++) {
			for(int j = 1; j <= dim; j++) {
				
				Float value = getItem(i, j);
				if( ( i == 2*io -1 && j == 2*jo - 1) || ( i == 2*jo && j == 2*io) ) { value = Math.min(getItem(i, j), new Float(-e)); }
				setItem(i, j, value);				
			}
		}
	}

	public void Q_vjo_eq_vio(int jo, int io){
		matrix res = new matrix(dim);
		
		matrix m1 = new matrix(dim);
		m1.copy(this);
		m1.Q_vjo_M_vio_P_e_leq_0(jo, io, 0);
	
		matrix m2 = new matrix(dim);
		m2.copy(this);
		m2.Q_vjo_M_vio_P_e_leq_0(jo, io, 0);
	
		res = pico_arriba(m1, m2);
		copy(res);
	}
	
	public void Q_vjo_neq_vio(int jo, int io){
		matrix res = new matrix(dim);
		
		matrix m1 = new matrix(dim);
		m1.copy(this);
		m1.Q_vjo_M_vio_P_e_leq_0(jo, io, 1);
	
		matrix m2 = new matrix(dim);
		m2.copy(this);
		m2.Q_vjo_M_vio_P_e_leq_0(io, jo, 1);
	
		res = union(m1, m2);
		copy(res);
	}
	
	private matrix union(matrix m, matrix n) {
		return pico_abajo(m, n);
	}

	private matrix pico_arriba(matrix m, matrix n) {
		// o sea m ^ n = minimo de cada elemento
		assert n.dim == m.dim;
	    matrix res = new matrix(n.dim); 
	    
	    for(int i = 1; i <= dim; i++) {
			for(int j = 1; j <= dim; j++) {
				
				Float m_ij = m.getItem(i, j);
				Float n_ij = n.getItem(i, j);
								
				Float value = null;
				if(Math.min(Math.abs(m_ij), Math.abs(n_ij)) == Math.abs(m_ij)){
					value = m_ij;
				}else{
					value = n_ij;
				}
				res.setItem(i, j, value);
			}
		}		
		return res;
	}
	
	private matrix pico_abajo(matrix m, matrix n) {
		// o sea m v n = maximo de cada elemento
		assert n.dim == m.dim;
	    matrix res = new matrix(n.dim); 
	    
	    for(int i = 1; i <= dim; i++) {
			for(int j = 1; j <= dim; j++) {
				
				Float m_ij = m.getItem(i, j);
				Float n_ij = n.getItem(i, j);
								
				Float value = null;
				if(Math.max(Math.abs(m_ij), Math.abs(n_ij)) == Math.abs(m_ij)){
					value = m_ij;
				}else{
					value = n_ij;
				}
				res.setItem(i, j, value);
			}
		}		
		return res;
	}

	//====================================================================================================//
	
	public String matrixToFormula(){
        
		Set<String> formula = new HashSet<String>();
        
		int index_i = 1;
		int index_j = 1;
		
		for(int i = 1; i <= dim; i = i + 2) {
			
			index_i = 1;
			for(int j = 1; j <= dim; j = j + 2) {
				
				int ic = compemento(i);
				int jc = compemento(j);
				
				if( i != j ){
					if( ( getItem(j,  i ).equals( getItem(ic, jc)) ) && ( getItem(ic, jc) != Float.POSITIVE_INFINITY)) 
						formula.add("V"+ String.valueOf(index_i)+" - V"+String.valueOf(index_j)+" <= "+String.valueOf(getItem(j, i) ));

					if( ( getItem(jc, i ).equals( getItem(ic, j )) ) && ( getItem(ic, j) != Float.POSITIVE_INFINITY)) 
	                	formula.add("V"+String.valueOf(index_i)+" + V"+String.valueOf(index_j)+" <= "+String.valueOf(getItem(jc, i) ));    
	                
	                if( ( getItem(i,  jc).equals( getItem(j, ic )) ) && ( getItem(j, ic) != Float.POSITIVE_INFINITY)) 
	                	formula.add("- V"+String.valueOf(index_i)+" - V"+String.valueOf(index_j)+" <= "+String.valueOf(getItem(i, jc) ));
	            
				}else{
					
					if( getItem(ic, i) != Float.POSITIVE_INFINITY) formula.add("V"+String.valueOf(index_i)+" <= "+String.valueOf( getItem(ic, i) / 2 ));
	                if( getItem(i, ic) != Float.POSITIVE_INFINITY) formula.add("V"+String.valueOf(index_i)+" >= "+String.valueOf(-getItem(i, ic) / 2 ));
	                
				}
				
				index_i++;
			}
			index_j++;
		}
		return formula.toString();		
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
		
		n = new matrix(6);
		n.vjo_assign_e(1, 4);
		n.vjo_assign_e(3, 3);
		System.out.println(n.toString());
		n.closure();
		System.out.println(n.toString());
		System.out.println(n.matrixToFormula());
	}
}
