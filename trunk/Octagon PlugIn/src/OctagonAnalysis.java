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
import edu.cmu.cs.crystal.tac.TACFlowAnalysis;

public class OctagonAnalysis extends AbstractCrystalMethodAnalysis{

	TACFlowAnalysis<OctagonLatticeElement> flowAnalysis;
	
	@Override
	public void analyzeMethod(MethodDeclaration d) {
		//String methodName = d.getName().getIdentifier();
		//getReporter().debugOut().println(methodName);
		//d.accept(new OctagonVisitor());
		
		OctagonTransferFunction tf = new OctagonTransferFunction();
		flowAnalysis = new TACFlowAnalysis<OctagonLatticeElement>(tf, getInput());
		d.accept(new OctagonVisitor());
	}

	@Override
	public String getName() {
		return "Octagon Analysis";
	}
	
	//The visitors of the analysis
	
	private class OctagonVisitor extends ASTVisitor{

		/* (non-Javadoc)
		 * @see org.eclipse.jdt.core.dom.ASTVisitor#endVisit(org.eclipse.jdt.core.dom.Assignment)
		 */
		@Override
		public void endVisit(Assignment node) {
			// TODO Auto-generated method stub
			super.endVisit(node);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jdt.core.dom.ASTVisitor#endVisit(org.eclipse.jdt.core.dom.Block)
		 */
		@Override
		public void endVisit(Block node) {
			// TODO Auto-generated method stub
			super.endVisit(node);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jdt.core.dom.ASTVisitor#endVisit(org.eclipse.jdt.core.dom.ConditionalExpression)
		 */
		@Override
		public void endVisit(ConditionalExpression node) {
			// TODO Auto-generated method stub
			super.endVisit(node);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jdt.core.dom.ASTVisitor#endVisit(org.eclipse.jdt.core.dom.DoStatement)
		 */
		@Override
		public void endVisit(DoStatement node) {
			// TODO Auto-generated method stub
			super.endVisit(node);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jdt.core.dom.ASTVisitor#endVisit(org.eclipse.jdt.core.dom.ForStatement)
		 */
		@Override
		public void endVisit(ForStatement node) {
			// TODO Auto-generated method stub
			super.endVisit(node);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jdt.core.dom.ASTVisitor#endVisit(org.eclipse.jdt.core.dom.IfStatement)
		 */
		@Override
		public void endVisit(IfStatement node) {
			// TODO Auto-generated method stub
			super.endVisit(node);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jdt.core.dom.ASTVisitor#endVisit(org.eclipse.jdt.core.dom.MarkerAnnotation)
		 */
		@Override
		public void endVisit(MarkerAnnotation node) {
			// TODO Auto-generated method stub
			super.endVisit(node);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jdt.core.dom.ASTVisitor#endVisit(org.eclipse.jdt.core.dom.VariableDeclarationStatement)
		 */
		@Override
		public void endVisit(VariableDeclarationStatement node) {
			// TODO Auto-generated method stub
			super.endVisit(node);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jdt.core.dom.ASTVisitor#endVisit(org.eclipse.jdt.core.dom.WhileStatement)
		 */
		@Override
		public void endVisit(WhileStatement node) {
			// TODO Auto-generated method stub
			super.endVisit(node);
		}
		
	}
}
