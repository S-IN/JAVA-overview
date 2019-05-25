// ==========================================================================
// APPLICATION TestAnimable : 
// -------------------------------------------------------------------------- 
// Classe Dessin (SERVICE MINIMUM)
// ==========================================================================

import java.awt.*;
import animationMG.*;

public class Dessin extends DessinAbstract
{
    public Color getCouleur()
    {
        return new Color(100, 200, 10);
    }

    public int getTransparence()
    {
        return 150;
    }
}