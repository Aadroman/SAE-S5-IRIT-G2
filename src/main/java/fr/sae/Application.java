package fr.sae;

import fr.irit.algebraictree.*;
import fr.sae.algebraictree.*;

public class Application {
    public ETreeNode createTree (TreeNode node) {
        if(node instanceof Projection) {
            return new EProjection((Projection)node);
        } else  if(node instanceof Selection) {
            return new ESelection((Selection)node);
        } else if(node instanceof Join) {
            return new EJoin((Join)node);
        }  else if(node instanceof Table) {
            return new ETable((Table) node);
        }

        return null;
    }

}