import edu.cmu.cs.crystal.simple.SimpleLatticeOperations;

public class OctagonOperations extends SimpleLatticeOperations<OctagonLatticeElement> {

	@Override
	public boolean atLeastAsPrecise(OctagonLatticeElement left,
			OctagonLatticeElement right) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public OctagonLatticeElement bottom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OctagonLatticeElement copy(OctagonLatticeElement original) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OctagonLatticeElement join(OctagonLatticeElement left,
			OctagonLatticeElement right) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public OctagonLatticeElement getDefault() {
		return bottom();		
	}
}
