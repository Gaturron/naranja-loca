import edu.cmu.cs.crystal.simple.SimpleLatticeOperations;

public class OctagonOperations extends SimpleLatticeOperations<OctagonLatticeElement> {

	@Override
	public boolean atLeastAsPrecise(OctagonLatticeElement left, OctagonLatticeElement right) {
		
		return left.matrix.atLeastAsPrecise(right.matrix);
	}

	@Override
	public OctagonLatticeElement bottom() {
		
		OctagonLatticeElement e = new OctagonLatticeElement();
		e.matrix = matrix.bottom(2);
		return e;
	}

	@Override
	public OctagonLatticeElement copy(OctagonLatticeElement original) {
		
		OctagonLatticeElement e = new OctagonLatticeElement();
		e.matrix.copy(original.matrix);
		return e;
	}

	@Override
	public OctagonLatticeElement join(OctagonLatticeElement left, OctagonLatticeElement right) {
		int dim = left.matrix.dim;
		matrix m = new matrix(dim);
		m.union(left.matrix, right.matrix);
		OctagonLatticeElement e = new OctagonLatticeElement();
		e.matrix = m;
		return e;
	}
	
	public OctagonLatticeElement getDefault() {
		
		return bottom();		
	}
}
