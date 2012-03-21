import org.eclipse.jdt.core.dom.MethodDeclaration;
import edu.cmu.cs.crystal.flow.ILatticeOperations;
import edu.cmu.cs.crystal.simple.AbstractingTransferFunction;
import edu.cmu.cs.crystal.tac.model.AssignmentInstruction;

public class OctagonTransferFunction extends AbstractingTransferFunction<OctagonLatticeElement>{

	private OctagonOperations ops = new OctagonOperations();
	
	matrix temp = new matrix(4);
	
	public OctagonLatticeElement createEntryValue(MethodDeclaration method) {
		OctagonLatticeElement def = new OctagonLatticeElement();
		def.matrix = new matrix(4);
		System.out.println("Hola1: "+method.toString());
		System.out.println(def.matrix.toString());

		//def.indexes.clear();
		

		System.out.println(temp.toString());
		temp.vjo_assign_e(1, 2);
		
		
		return def;
	}

	@Override
	public ILatticeOperations<OctagonLatticeElement> getLatticeOperations() {


		System.out.println("Hola2 "+temp.toString());
		temp.vjo_assign_e(1, 3);
		
		return ops;
	}
	
	@Override
	public OctagonLatticeElement transfer(AssignmentInstruction instr, OctagonLatticeElement value) {
		System.out.println("Hola3 "+instr.toString());
		
		System.out.println(temp.toString());
		temp.vjo_assign_e(1, 4);
		
		return value;
	}
}
