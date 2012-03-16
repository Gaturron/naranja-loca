import org.eclipse.jdt.core.dom.MethodDeclaration;
import edu.cmu.cs.crystal.flow.ILatticeOperations;
import edu.cmu.cs.crystal.simple.AbstractingTransferFunction;

public class OctagonTransferFunction extends AbstractingTransferFunction<OctagonLatticeElement>{

	private OctagonOperations ops = new OctagonOperations();
	
	@Override
	public OctagonLatticeElement createEntryValue(MethodDeclaration method) {
		OctagonLatticeElement def = ops.getDefault();

		//def.indexes.clear();
		
		return def;
	}

	@Override
	public ILatticeOperations<OctagonLatticeElement> getLatticeOperations() {
		return ops;
	}
}
