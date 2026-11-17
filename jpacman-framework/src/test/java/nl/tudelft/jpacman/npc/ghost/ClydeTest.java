package nl.tudelft.jpacman.npc.ghost;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ClydeTest {

    private GhostMapParser ghostMapParser;
    private PlayerFactory playerFactory;

    @BeforeEach
    public void setUp(){
        PacManSprites pacManSprites = new PacManSprites();
        GhostFactory ghostFactory = new GhostFactory(pacManSprites);
        LevelFactory levelFactory = new LevelFactory(pacManSprites, ghostFactory);
        BoardFactory boardFactory = new BoardFactory(pacManSprites);

        ghostMapParser = new GhostMapParser(levelFactory,boardFactory,ghostFactory);
        playerFactory = new PlayerFactory(pacManSprites);
    }

    /**
     * Vérifie que le prochain déplacement va etre à l'oposé
     */
    @Test
    public void testTooClose(){

        List<String> map = Lists.newArrayList(
            "######## ",
            "#P   C # ",
            "######## ",
            "######## "
        );

        Level level = ghostMapParser.parseMap(map);
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.EAST);
        level.registerPlayer(player);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class,level.getBoard());
        Assertions.assertNotNull(clyde);
        Optional<Direction> directionOptimal = clyde.nextAiMove();
        assertThat(directionOptimal).isEqualTo(Optional.of(Direction.EAST));
    }

    /**
     * Si il est trop loin, il va se rapprocher du joueur
     */
    @Test
    public void testFarPlayer(){
        List<String> map = Lists.newArrayList(
            "########",
            "#P #   #",
            "#      #",
            "#      #",
            "#      #",
            "#      #",
            "#      #",
            "#      #",
            "#      #",
            "#      #",
            "#      #",
            "#      #",
            "#      #",
            "#C     #",
            "########"
        );

        Level level = ghostMapParser.parseMap(map);
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.EAST);
        level.registerPlayer(player);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class,level.getBoard());
        Assertions.assertNotNull(clyde);
        Optional<Direction> directionOptimal = clyde.nextAiMove();
        assertThat(directionOptimal).isEqualTo(Optional.of(Direction.NORTH));
    }

    /**
     * Test s'il y a un obstacle
     */
    @Test
    public void testObstacle(){
        List<String> map = Lists.newArrayList(
            "########",
            "#P #  C#",
            "########",
            "########"
        );

        Level level = ghostMapParser.parseMap(map);
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.EAST);
        level.registerPlayer(player);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class,level.getBoard());
        Assertions.assertNotNull(clyde);
        assertThat(clyde.nextAiMove()).isEqualTo(Optional.empty());
    }
}
