import edu.cmu.cs.crystal.simple.SimpleLatticeOperations;

public class OctagonOperations extends SimpleLatticeOperations<OctagonLatticeElement> {

	@Override
	public boolean atLeastAsPrecise(OctagonLatticeElement left, OctagonLatticeElement right) {
		
		//return left.matrix.atLeastAsPrecise(right.matrix);
		return left.number <= right.number;
	}

	@Override
	public OctagonLatticeElement bottom() {
		
		OctagonLatticeElement e = new OctagonLatticeElement();
		//e.matrix = matrix.bottom(4);
		e.number = 0;
		return e;
	}

	@Override
	public OctagonLatticeElement copy(OctagonLatticeElement original) {
		
		OctagonLatticeElement e = new OctagonLatticeElement();
		//e.matrix.copy(original.matrix);
		
		e.number = original.number;		
		return e;
	}

	@Override
	public OctagonLatticeElement join(OctagonLatticeElement left, OctagonLatticeElement right) {
		
		//esto hay que cambiarlo
		/*int dim = left.matrix.dim;
		matrix m = new matrix(dim);
		m.union(left.matrix, right.matrix);
		OctagonLatticeElement e = new OctagonLatticeElement();
		e.matrix = m;
		return e;*/
		OctagonLatticeElement e = new OctagonLatticeElement();
		e.number = left.number + right.number;
		
		return e;
	}
	
	public OctagonLatticeElement getDefault() {
		
		return bottom();		
	}
}
