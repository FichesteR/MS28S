package com.fbergeron.solitaire;

import com.fbergeron.card.Card;
import com.fbergeron.card.ClassicCard;
import com.fbergeron.card.ClassicDeck;
import com.fbergeron.card.Stack;
import com.fbergeron.util.DialogMsg;
import com.fbergeron.util.FrameAbout;
import com.fbergeron.util.ImagePanel;
import com.fbergeron.util.WindowManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class GameWon extends Solitaire {
    public boolean isGameWon() {
        boolean gameWon = this.deck.isEmpty() && this.revealedCards.isEmpty();
        if( gameWon )
            for( int i = 0; i < Solitaire.SOL_STACK_CNT && gameWon; i++ )
                gameWon = gameWon && solStack[ i ].isEmpty();
        return( gameWon );
    }
}
