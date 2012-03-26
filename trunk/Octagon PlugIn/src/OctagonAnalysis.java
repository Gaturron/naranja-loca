import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import edu.cmu.cs.crystal.AbstractCrystalMethodAnalysis;
import edu.cmu.cs.crystal.simple.SimpleTACFlowAnalysis;

public class OctagonAnalysis extends AbstractCrystalMethodAnalysis{

	SimpleTACFlowAnalysis<OctagonLatticeElement> flowAnalysis;
	
	@Override
	public void analyzeMethod(MethodDeclaration d) {
		String methodName = d.getName().getIdentifier();
		getReporter().debugOut().println(methodName);
		
		System.out.println("Analyze Method Starts "+methodName);
		
		OctagonTransferFunction tf = new OctagonTransferFunction();
		flowAnalysis = new SimpleTACFlowAnalysis<OctagonLatticeElement>(tf, getInput());
		d.accept(new OctagonVisitor());
	}

	@Override
	public String getName() {
		return "Octagon Analysis";
	}
	
	//The visitors of the analysis
	
	private class OctagonVisitor extends ASTVisitor{	
		
		@Override
		public void endVisit(VariableDeclarationStatement node) {
			System.out.print("Asignacion "+node.toString());
			
			OctagonLatticeElement OctBefore = flowAnalysis.getResultsAfterAST(node);
			System.out.println("OctBefore: ");
			System.out.println(OctBefore.matrix.toString());
		}
		
		@Override
		public void endVisit(Assignment node) {
			// TODO Auto-generated method stub
			super.endVisit(node);
		}

		@Override
		public void endVisit(Block node) {
			// TODO Auto-generated method stub
			super.endVisit(node);
		}

		@Override
		public void endVisit(ConditionalExpression node) {
			// TODO Auto-generated method stub
			super.endVisit(node);
		}

		@Override
		public void endVisit(DoStatement node) {
			// TODO Auto-generated method stub
			super.endVisit(node);
		}

		@Override
		public void endVisit(ForStatement node) {
			// TODO Auto-generated method stub
			super.endVisit(node);
		}
		
		@Override
		public void endVisit(IfStatement node) {
			// TODO Auto-generated method stub
			System.out.println("If");
			super.endVisit(node);
		}

		@Override
		public void endVisit(MarkerAnnotation node) {
			// TODO Auto-generated method stub
			super.endVisit(node);
		}

		@Override
		public void endVisit(WhileStatement node) {
			// TODO Auto-generated method stub
			super.endVisit(node);
		}
		
	}
}
