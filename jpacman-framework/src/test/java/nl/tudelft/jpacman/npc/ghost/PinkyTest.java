package nl.tudelft.jpacman.npc.ghost;

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

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PinkyTest {
    private GhostMapParser ghostMapParser;
    private PlayerFactory playerFactory;

    @BeforeEach
    public void setUp(){
        PacManSprites pacManSprites = new PacManSprites();
        GhostFactory ghostFactory = new GhostFactory(pacManSprites);
        LevelFactory levelFactory = new LevelFactory(pacManSprites,ghostFactory);
        BoardFactory boardFactory = new BoardFactory(pacManSprites);

        ghostMapParser = new GhostMapParser(levelFactory,boardFactory,ghostFactory);
        playerFactory = new PlayerFactory(pacManSprites);
    }

    @Test
    public void faceSouth(){
        List<String> map = Lists.newArrayList(
            "########",
            "#S     #",
            "#P     #",
            "#      #",
            "#      #",
            "#      #",
            "#      #",
            "########"
        );

        Level level = ghostMapParser.parseMap(map);
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.SOUTH);
        level.registerPlayer(player);

        Pinky pinky = Navigation.findUnitInBoard(Pinky.class,level.getBoard());
        Assertions.assertNotNull(pinky);
        assertThat(pinky.nextAiMove()).isEqualTo(Optional.of(Direction.SOUTH));
    }

    @Test
    public void faceWest(){
        List<String> map = Lists.newArrayList(
            "########",
            "#      #",
            "#    PS#",
            "#      #",
            "#      #",
            "#      #",
            "#      #",
            "########"
        );

        Level level = ghostMapParser.parseMap(map);
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.WEST);
        level.registerPlayer(player);

        Pinky pinky = Navigation.findUnitInBoard(Pinky.class,level.getBoard());
        Assertions.assertNotNull(pinky);
        assertThat(pinky.nextAiMove()).isEqualTo(Optional.of(Direction.WEST));
    }

    @Test
    public void faceEast(){
        List<String> map = Lists.newArrayList(
            "########",
            "#      #",
            "#      #",
            "#SP    #",
            "#      #",
            "#      #",
            "#      #",
            "########"
        );

        Level level = ghostMapParser.parseMap(map);
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.EAST);
        level.registerPlayer(player);

        Pinky pinky = Navigation.findUnitInBoard(Pinky.class,level.getBoard());
        Assertions.assertNotNull(pinky);
        assertThat(pinky.nextAiMove()).isEqualTo(Optional.of(Direction.EAST));
    }

    @Test
    public void faceNorth(){
        List<String> map = Lists.newArrayList(
            "########",
            "#      #",
            "#      #",
            "#      #",
            "#      #",
            "#      #",
            "#S    P#",
            "########"
        );

        Level level = ghostMapParser.parseMap(map);
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.NORTH);
        level.registerPlayer(player);

        Pinky pinky = Navigation.findUnitInBoard(Pinky.class,level.getBoard());
        Assertions.assertNotNull(pinky);
        assertThat(pinky.nextAiMove()).isEqualTo(Optional.of(Direction.NORTH));
    }

    @Test
    public void faceNorthBis(){
        List<String> map = Lists.newArrayList(
            "###########",
            "#         #",
            "# S       #",
            "#         #",
            "#         #",
            "#         #",
            "#     P   #",
            "###########"
        );

        Level level = ghostMapParser.parseMap(map);
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.NORTH);
        level.registerPlayer(player);

        Pinky pinky = Navigation.findUnitInBoard(Pinky.class,level.getBoard());
        Assertions.assertNotNull(pinky);
        assertThat(pinky.nextAiMove()).isEqualTo(Optional.of(Direction.EAST));
    }
}
