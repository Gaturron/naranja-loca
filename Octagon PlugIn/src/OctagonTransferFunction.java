import org.eclipse.jdt.core.dom.MethodDeclaration;
import edu.cmu.cs.crystal.flow.ILatticeOperations;
import edu.cmu.cs.crystal.simple.AbstractingTransferFunction;
import edu.cmu.cs.crystal.tac.model.AssignmentInstruction;
import edu.cmu.cs.crystal.tac.model.CopyInstruction;
import edu.cmu.cs.crystal.tac.model.LoadLiteralInstruction;
import edu.cmu.cs.crystal.tac.model.NewObjectInstruction;
import edu.cmu.cs.crystal.tac.model.SourceVariableDeclaration;

public class OctagonTransferFunction extends AbstractingTransferFunction<OctagonLatticeElement>{

	private OctagonOperations ops = new OctagonOperations();
	
	public OctagonLatticeElement createEntryValue(MethodDeclaration method) {
		OctagonLatticeElement def = new OctagonLatticeElement();
		
		System.out.println("	Hola: "+method.getName().getIdentifier());
		
		return def;
	}

	@Override
	public ILatticeOperations<OctagonLatticeElement> getLatticeOperations() {

		System.out.println("	Hola2 ");
		
		return ops;
	}
	
	@Override
	public OctagonLatticeElement transfer(LoadLiteralInstruction instr, OctagonLatticeElement value) {
		
		System.out.println("	Hola3 "+value.number);
		value.number++;
		return value;
	}
	//ACA se puede agregar todas las funciones de transfer
}
