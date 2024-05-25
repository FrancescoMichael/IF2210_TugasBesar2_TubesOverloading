package oop;

import java.io.File;
import java.net.URL;
// import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import java.io.FileInputStream;

import oop.plugin.PluginInterface;
import oop.plugin.PluginLoader;
import javafx.util.Duration;
import oop.card.Card;
import oop.card.item.*;
import oop.exceptionkerajaan.BaseException;
import oop.gamemaster.GameMaster;
import oop.player.Player;
import oop.card.product.*;

public class FieldController implements Initializable {

    @FXML
    private ImageView ActiveDeck1;

    @FXML
    private ImageView ActiveDeck2;

    @FXML
    private ImageView ActiveDeck3;

    @FXML
    private ImageView ActiveDeck4;

    @FXML
    private ImageView ActiveDeck5;

    @FXML
    private ImageView ActiveDeck6;

    @FXML
    private ImageView grid11;

    @FXML
    private ImageView grid12;

    @FXML
    private ImageView grid13;

    @FXML
    private ImageView grid14;

    @FXML
    private ImageView grid15;

    @FXML
    private ImageView grid21;

    @FXML
    private ImageView grid22;

    @FXML
    private ImageView grid23;

    @FXML
    private ImageView grid24;

    @FXML
    private ImageView grid25;

    @FXML
    private ImageView grid31;

    @FXML
    private ImageView grid32;

    @FXML
    private ImageView grid33;

    @FXML
    private ImageView grid34;

    @FXML
    private ImageView grid35;

    @FXML
    private ImageView grid41;

    @FXML
    private ImageView grid42;

    @FXML
    private ImageView grid43;

    @FXML
    private ImageView grid44;

    @FXML
    private ImageView grid45;

    @FXML
    private Pane plane11;

    @FXML
    private Pane plane12;

    @FXML
    private Pane plane13;

    @FXML
    private Pane plane14;

    @FXML
    private Pane plane15;

    @FXML
    private Pane plane21;

    @FXML
    private Pane plane22;

    @FXML
    private Pane plane23;

    @FXML
    private Pane plane24;

    @FXML
    private Pane plane25;

    @FXML
    private Pane plane31;

    @FXML
    private Pane plane32;

    @FXML
    private Pane plane33;

    @FXML
    private Pane plane34;

    @FXML
    private Pane plane35;

    @FXML
    private Pane plane41;

    @FXML
    private Pane plane42;

    @FXML
    private Pane plane43;

    @FXML
    private Pane plane44;

    @FXML
    private Pane plane45;

    @FXML
    private ImageView activeCard1;

    @FXML
    private ImageView activeCard2;

    @FXML
    private ImageView activeCard3;

    @FXML
    private ImageView activeCard4;

    @FXML
    private ImageView activeCard5;

    @FXML
    private ImageView activeCard6;

    @FXML
    private ImageView nextTurnBtn;

    @FXML
    private Button bearAttackButton;

    @FXML
    private Label timerLabel;

    @FXML
    private ImageView LoadPlugin;

    @FXML
    private ImageView LoadState;

    @FXML
    private ImageView SaveState;

    @FXML
    private ImageView toLadangLawan;

    @FXML
    private ImageView toLadangku;

    @FXML
    private ImageView LoadPlugin1;

    @FXML
    private ImageView LoadState1;

    @FXML
    private ImageView SaveState1;

    @FXML
    private ImageView toLadangLawan1;

    @FXML
    private ImageView toLadangku1;

    @FXML
    private ImageView toToko;

    @FXML
    private ImageView toToko1;

    @FXML
    private ImageView PanenBtn;

    @FXML
    private ImageView AnimalImage;

    @FXML
    private Label AnimalName;

    @FXML
    private ImageView ContainerPanen;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private Label label5;

    @FXML
    private Label label6;
    @FXML
    private ImageView kosong11;

    @FXML
    private ImageView kosong12;

    @FXML
    private ImageView kosong13;

    @FXML
    private ImageView kosong14;

    @FXML
    private ImageView kosong15;

    @FXML
    private ImageView kosong21;

    @FXML
    private ImageView kosong22;

    @FXML
    private ImageView kosong23;

    @FXML
    private ImageView kosong24;

    @FXML
    private ImageView kosong25;

    @FXML
    private ImageView kosong31;

    @FXML
    private ImageView kosong32;

    @FXML
    private ImageView kosong33;

    @FXML
    private ImageView kosong34;

    @FXML
    private ImageView kosong35;

    @FXML
    private ImageView kosong41;

    @FXML
    private ImageView kosong42;

    @FXML
    private ImageView kosong43;

    @FXML
    private ImageView kosong44;

    @FXML
    private ImageView kosong45;

    @FXML
    private ImageView CloseBtn;

    @FXML
    private ImageView clickableGrid11;

    @FXML
    private Label firstPlayerMoney;

    @FXML
    private Label secondPlayerMoney;

    @FXML
    private Label turn;

    @FXML
    private ImageView titleplayer1;

    @FXML
    private ImageView titleplayer2;

    @FXML
    private ImageView titleplayer1turn;

    @FXML
    private ImageView titleplayer2turn;

    @FXML
    private ImageView background;

    @FXML
    private ImageView close;

    @FXML
    private ImageView load_button;

    @FXML
    private ImageView load_folder;

    @FXML
    private ImageView load_folder_field;

    @FXML
    private ImageView load_format;

    @FXML
    private ImageView load_format_field;

    @FXML
    private ImageView load_title;

    @FXML
    private ImageView plugin_button;

    @FXML
    private ImageView plugin_file;

    @FXML
    private ImageView plugin_file_field;

    @FXML
    private ImageView plugin_title;

    @FXML
    private ImageView save_button;

    @FXML
    private ImageView save_folder;

    @FXML
    private ImageView save_folder_field;

    @FXML
    private ImageView save_format;

    @FXML
    private ImageView save_format_field;

    @FXML
    private ImageView save_title;

    @FXML
    private Label chooseFilePluginLabel;

    @FXML
    private ComboBox<String> saveFormatComboBox;

    @FXML
    private Label chooseSaveFolderLabel;

    @FXML
    private ComboBox<String> loadFormatComboBox;

    @FXML
    private Label chooseLoadFolderLabel;

    @FXML
    private ImageView toko_bg;

    @FXML
    private ImageView toko_title;

    @FXML
    private ImageView toko_deck1;

    @FXML
    private ImageView toko_deck2;

    @FXML
    private ImageView toko_deck3;

    @FXML
    private ImageView toko_deck4;

    @FXML
    private ImageView toko_deck5;

    @FXML
    private ImageView toko_deck6;

    @FXML
    private ImageView toko_deck7;

    @FXML
    private ImageView toko_deck8;

    @FXML
    private ImageView toko_deck9;

    @FXML
    private ImageView toko_deck10;

    @FXML
    private ImageView toko_deck11;

    @FXML
    private ImageView toko_deck12;

    @FXML
    private ImageView toko_back;

    @FXML
    private ImageView toko_daging_domba;

    @FXML
    private ImageView toko_jagung;

    @FXML
    private ImageView toko_daging_kuda;

    @FXML
    private ImageView toko_sirip_hiu;

    @FXML
    private ImageView toko_stroberi;

    @FXML
    private ImageView toko_susu;

    @FXML
    private ImageView toko_telur;

    @FXML
    private ImageView toko_labu;

    @FXML
    private ImageView toko_daging_beruang;

    @FXML
    private ImageView PopUpToko;

    @FXML
    private ImageView ImageToko;

    @FXML
    private Label Harga;

    @FXML
    private ImageView BuyButton;

    @FXML
    private ImageView SellButton;

    @FXML
    private Label AnimalNameToko;

    @FXML
    private ImageView ClosePopUpToko;

    @FXML
    private Label LabelHarga;

    @FXML
    private Label LabelKuantitas;

    @FXML
    private ImageView shuffleBG;

    @FXML
    private ImageView shufflecard1;

    @FXML
    private ImageView shufflecard2;

    @FXML
    private ImageView shufflecard3;

    @FXML
    private ImageView shufflecard4;

    @FXML
    private ImageView retry;

    @FXML
    private ImageView okshuffle;

    @FXML
    private Label Kuantitas;

    @FXML
    private Label deckLeft;

    @FXML
    private ImageView home_start_bg;

    @FXML
    private ImageView home_main_title;

    @FXML
    private ImageView home_start_button;

    @FXML
    private ImageView home_nim;

    @FXML
    private ImageView winning_bg;

    @FXML
    private Label menang_player_info;

    @FXML
    private ImageView retrygame;

    @FXML
    private ImageView exitgame;

    @FXML
    private MediaView mediaView;

    @FXML
    private MediaView clickMusic;

    @FXML
    private MediaView bearMusic;

    @FXML
    private MediaView placeMusic;

    @FXML
    private MediaView moneyMusic;

    @FXML
    private ImageView clock;

    private Timeline countdownTimeline;

    private GameMaster gameMaster = new GameMaster();

    GlowButtonMaker glowButtonMaker = new GlowButtonMaker();

    DraggableMaker draggableMaker;

    ImageView[][] matrixGrid;

    ImageView[][] matrixCardInField;

    ImageView[] listActiveDeck;

    int currentProduct;

    String currentProductName;

    MediaPlayer clickMediaPlayer;

    MediaPlayer bearMediaPlayer;

    MediaPlayer placeMediaPlayer;

    MediaPlayer moneyMediaPlayer;

    public DraggableMaker getDraggableMaker() {
        return this.draggableMaker;
    }

    public void updateDeckLeft() {
        int cardInDeck = gameMaster.getCurrentPlayer().getCardDeckLeft();
        deckLeft.setText(cardInDeck + "/40");
    }

    public void ShuffleVisible(ArrayList<Card> cardNames) {
        shuffleBG.setVisible(true);
        shufflecard1.setVisible(true);
        shufflecard2.setVisible(true);
        shufflecard3.setVisible(true);
        shufflecard4.setVisible(true);
        retry.setVisible(true);
        okshuffle.setVisible(true);

        ImageView[] shuffleCards = {
                shufflecard1, shufflecard2, shufflecard3, shufflecard4
        };

        for (int i = 0; i < cardNames.size() && i < shuffleCards.length; i++) {
            // handle if null
            if (cardNames.get(i).getPathToImg() == null) {
                shuffleCards[i].setImage(new Image("/assets/OOP 2/OOP 2/misc/empty_cards_deck.png"));
            } else {
                shuffleCards[i].setImage(new Image(cardNames.get(i).getPathToImg()));
            }
        }
    }

    private boolean gameStarted = false;

    private MediaPlayer mainMediaPlayer;

    public void BearMediaPlayerPlay() {
        mainMediaPlayer.stop();
        bearMediaPlayer.play();
    }

    public void BearMediaPlayerStop() {
        mainMediaPlayer.play();
        bearMediaPlayer.stop();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // music
        String musicFile = "/assets/OOP 2/OOP 2/music/mainMusic.mp3";
        String clickPath = "/assets/OOP 2/OOP 2/music/clickButton.mp3";
        String bearPath = "/assets/OOP 2/OOP 2/music/bearAttack.mp3";
        String placePath = "/assets/OOP 2/OOP 2/music/placeSound.mp3";
        String moneyPath = "/assets/OOP 2/OOP 2/music/moneyMusic.mp3";
        URL clickUrl = getClass().getResource(clickPath);
        URL musicUrl = getClass().getResource(musicFile);
        URL bearUrl = getClass().getResource(bearPath);
        URL placeUrl = getClass().getResource(placePath);
        URL moneyUrl = getClass().getResource(moneyPath);
        if (musicUrl != null) {
            Media media = new Media(musicUrl.toExternalForm());
            mainMediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mainMediaPlayer);

            Media clickMedia = new Media(clickUrl.toExternalForm());
            clickMediaPlayer = new MediaPlayer(clickMedia);
            clickMusic.setMediaPlayer(clickMediaPlayer);

            Media bearMedia = new Media(bearUrl.toExternalForm());
            bearMediaPlayer = new MediaPlayer(bearMedia);
            bearMusic.setMediaPlayer(bearMediaPlayer);

            Media placeMedia = new Media(placeUrl.toExternalForm());
            placeMediaPlayer = new MediaPlayer(placeMedia);
            placeMusic.setMediaPlayer(placeMediaPlayer);

            Media moneyMedia = new Media(moneyUrl.toExternalForm());
            moneyMediaPlayer = new MediaPlayer(moneyMedia);
            moneyMusic.setMediaPlayer(moneyMediaPlayer);
        } else {
            System.out.println("Music file not found");
        }
        mainMediaPlayer.setCycleCount(-1);
        mainMediaPlayer.setOnPlaying(() -> {
            mainMediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.toSeconds() >= 78) {
                    mainMediaPlayer.seek(Duration.ZERO);
                }
            });
        });
        clickMediaPlayer.setOnPlaying(() -> {
            clickMediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.toMillis() >= 400) {
                    clickMediaPlayer.stop();
                }
            });
        });
        bearMediaPlayer.setOnPlaying(() -> {
            bearMediaPlayer.seek(Duration.seconds(5));
        });
        mainMediaPlayer.play();

        glowButtonMaker.setGlow(home_start_button);
        home_start_button.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            if (!gameStarted) {
                // mediaPlayer.stop();
                timerLabel.setVisible(false);
                initializeGameComponents();
                home_main_title.setVisible(gameStarted);
                home_start_bg.setVisible(gameStarted);
                home_start_button.setVisible(gameStarted);
                home_nim.setVisible(gameStarted);
                gameStarted = true;
            }
        });
    }

    public void initializeGameComponents() {
        glowButtonMaker.setGlow(retry);
        retry.setOnMouseClicked(event -> {
            gameMaster.shuffle();
            ShuffleVisible(gameMaster.getCurrentShuffle());
        });

        glowButtonMaker.setGlow(okshuffle);
        okshuffle.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            shuffleBG.setVisible(false);
            shufflecard1.setVisible(false);
            shufflecard1.setImage(new Image("/assets/OOP 2/OOP 2/misc/empty_cards_deck.png"));
            shufflecard2.setVisible(false);
            shufflecard2.setImage(new Image("/assets/OOP 2/OOP 2/misc/empty_cards_deck.png"));
            shufflecard3.setVisible(false);
            shufflecard3.setImage(new Image("/assets/OOP 2/OOP 2/misc/empty_cards_deck.png"));
            shufflecard4.setVisible(false);
            shufflecard4.setImage(new Image("/assets/OOP 2/OOP 2/misc/empty_cards_deck.png"));
            retry.setVisible(false);
            okshuffle.setVisible(false);
            try {
                gameMaster.doneShuffling(timerLabel, this, clock);
                updateDeckLeft();
            } catch (BaseException e) {
                System.out.println(e.getMessage());

            }
        });

        hideAll();
        glowButtonMaker.setGlow(close);
        glowButtonMaker.setGlow(load_button);
        glowButtonMaker.setGlow(plugin_button);
        glowButtonMaker.setGlow(save_button);
        glowButtonMaker.setGlow(toko_back);

        glowButtonMaker.setGlow(SellButton);
        glowButtonMaker.setGlow(BuyButton);
        glowButtonMaker.setGlow(ClosePopUpToko);

        close.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            hideAll();
            // reset combobox and label
            saveFormatComboBox.setValue(null);
            loadFormatComboBox.setValue(null);
            chooseLoadFolderLabel.setText("Click here to choose folder!");
            chooseSaveFolderLabel.setText("Click here to choose folder!");
            loadGridActiveDeck();
        });

        toko_back.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            hideAll();
            loadGridActiveDeck();
            loadOther();
        });

        chooseFilePluginLabel.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            handleFileChoose();
        });

        chooseSaveFolderLabel.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            handleSaveFolderChoose();
        });

        chooseLoadFolderLabel.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            handleLoadFolderChoose();
        });

        saveFormatComboBox.getItems().addAll("txt");
        loadFormatComboBox.getItems().addAll("txt");

        glowButtonMaker.setGlow(save_button);
        save_button.setOnMouseClicked(event -> {
            handleSave();
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
        });
        glowButtonMaker.setGlow(load_button);
        load_button.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            handleLoad();
        });
        glowButtonMaker.setGlow(plugin_button);
        plugin_button.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            handlePlugin();
        });

        titleplayer1turn.setVisible(true);
        titleplayer2turn.setVisible(false);
        List<Player> listPlayer = new ArrayList<>();
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");

        listPlayer.add(player1);
        listPlayer.add(player2);
        gameMaster.setListPlayer(listPlayer);
        gameMaster.setCurrentFieldPlayer(player1);
        gameMaster.shuffle();
        ShuffleVisible(gameMaster.getCurrentShuffle());

        // initialize matrixgrid and matrixCardInField
        matrixGrid = new ImageView[][] {
                { grid11, grid12, grid13, grid14, grid15 },
                { grid21, grid22, grid23, grid24, grid25 },
                { grid31, grid32, grid33, grid34, grid35 },
                { grid41, grid42, grid43, grid44, grid45 }
        };

        matrixCardInField = new ImageView[][] {
                { kosong11, kosong12, kosong13, kosong14, kosong15 },
                { kosong21, kosong22, kosong23, kosong24, kosong25 },
                { kosong31, kosong32, kosong33, kosong34, kosong35 },
                { kosong41, kosong42, kosong43, kosong44, kosong45 }
        };

        // initialize money and turn
        firstPlayerMoney.setText(gameMaster.getListPlayers().get(0).getGulden() + "");
        secondPlayerMoney.setText(gameMaster.getListPlayers().get(1).getGulden() + "");
        turn.setText((gameMaster.getCurrentTurn() + 1) + "");

        listActiveDeck = new ImageView[] {
                activeCard1, activeCard2, activeCard3,
                activeCard4, activeCard5, activeCard6
        };

        loadGridActiveDeck();

        label1.setText("BERAT:");
        label3.setText("ITEM AKTIF:");

        glowButtonMaker.setGlow(CloseBtn);
        CloseBtn.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            setPanenPageVisibility(false, true);
        });
        CloseBtn.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            setPanenPageVisibility(false, true);
        });

        glowButtonMaker.setGlow(PanenBtn);

        glowButtonMaker.setGlow(nextTurnBtn);
        nextTurnBtn.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            // playGif("/assets/OOP 2/OOP 2/gif/bearAttack.gif", grid11);
            if (gameMaster.getCurrentTurn() > 18) {
                setWinningPageVisibility(true);
            } else {
                if (!gameMaster.isBearAttack()) {
                    try {
                        gameMaster.next();
                        // example of shuffle and done shuffling

                        gameMaster.shuffle();
                        ShuffleVisible(gameMaster.getCurrentShuffle());
                    } catch (BaseException e) {

                    }

                    turn.setText(gameMaster.getCurrentTurn() + 1 + "");
                    gameMaster.setCurrentFieldPlayer(gameMaster.getCurrentPlayer());
                    loadGridActiveDeck();
                    toLadangku1.setVisible(true);
                    toLadangLawan1.setVisible(false);
                    toToko1.setVisible(false);
                    SaveState1.setVisible(false);
                    LoadPlugin1.setVisible(false);
                    LoadState1.setVisible(false);

                    if (gameMaster.getCurrentTurn() % 2 == 1) {
                        titleplayer1turn.setVisible(false);
                        titleplayer2turn.setVisible(true);
                    } else {
                        titleplayer1turn.setVisible(true);
                        titleplayer2turn.setVisible(false);
                    }
                }
            }
        });

        glowButtonMaker.setGlow(toLadangLawan);
        toLadangLawan.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            if (!gameMaster.isBearAttack()) {
                gameMaster
                        .setCurrentFieldPlayer(gameMaster.getListPlayers().get((gameMaster.getCurrentTurn() + 1) % 2));
                loadGridActiveDeck();
                toLadangLawan1.setVisible(true);
                toLadangku1.setVisible(false);
                toToko1.setVisible(false);
                SaveState1.setVisible(false);
                LoadPlugin1.setVisible(false);
                LoadState1.setVisible(false);
            }
        });
        glowButtonMaker.setGlow(toLadangku);
        toLadangku1.setVisible(true);
        toLadangku.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            gameMaster.setCurrentFieldPlayer(gameMaster.getCurrentPlayer());
            loadGridActiveDeck();
            toLadangku1.setVisible(true);
            toLadangLawan1.setVisible(false);
            toToko1.setVisible(false);
            SaveState1.setVisible(false);
            LoadPlugin1.setVisible(false);
            LoadState1.setVisible(false);
        });
        glowButtonMaker.setGlow(toToko);
        toToko.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            if (!gameMaster.isBearAttack()) {
                toToko1.setVisible(true);
                toLadangLawan1.setVisible(false);
                toLadangku1.setVisible(false);
                SaveState1.setVisible(false);
                LoadPlugin1.setVisible(false);
                LoadState1.setVisible(false);
            }
        });
        glowButtonMaker.setGlow(SaveState);
        SaveState.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            if (!gameMaster.isBearAttack()) {
                SaveState1.setVisible(true);
                toToko1.setVisible(false);
                toLadangLawan1.setVisible(false);
                toLadangku1.setVisible(false);
                LoadPlugin1.setVisible(false);
                LoadState1.setVisible(false);
            }
        });
        glowButtonMaker.setGlow(LoadPlugin);
        LoadPlugin.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            if (!gameMaster.isBearAttack()) {
                LoadPlugin1.setVisible(true);
                toToko1.setVisible(false);
                toLadangLawan1.setVisible(false);
                toLadangku1.setVisible(false);
                SaveState1.setVisible(false);
                LoadState1.setVisible(false);
            }
        });
        glowButtonMaker.setGlow(LoadState);
        LoadState.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            if (!gameMaster.isBearAttack()) {
                LoadState1.setVisible(true);
                toToko1.setVisible(false);
                toLadangLawan1.setVisible(false);
                toLadangku1.setVisible(false);
                SaveState1.setVisible(false);
                LoadPlugin1.setVisible(false);
            }
        });

        draggableMaker = new DraggableMaker(this);

        draggableMaker.makeDraggable(activeCard1, matrixGrid, gameMaster, false);
        draggableMaker.makeDraggable(activeCard2, matrixGrid, gameMaster, false);
        draggableMaker.makeDraggable(activeCard3, matrixGrid, gameMaster, false);
        draggableMaker.makeDraggable(activeCard4, matrixGrid, gameMaster, false);
        draggableMaker.makeDraggable(activeCard5, matrixGrid, gameMaster, false);
        draggableMaker.makeDraggable(activeCard6, matrixGrid, gameMaster, false);

        // bearAttackButton.setOnAction(event -> simulateBearAttack( ));
        bearAttackButton.setVisible(false);

        LoadPlugin.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            setState("LoadPlugin");
        });

        LoadState.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            setState("LoadState");
        });

        SaveState.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            setState("SaveState");
        });

        toToko.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            setState("TokoState");
        });

        ClosePopUpToko.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            PopUpToko.setVisible(false);
            ImageToko.setVisible(false);
            AnimalNameToko.setVisible(false);
            ClosePopUpToko.setVisible(false);
            Harga.setVisible(false);
            BuyButton.setVisible(false);
            SellButton.setVisible(false);
            LabelHarga.setVisible(false);
            LabelKuantitas.setVisible(false);
            Kuantitas.setVisible(false);
        });
    }

    public void setWinningPageVisibility(boolean visible) {
        winning_bg.setVisible(visible);
        menang_player_info.setVisible(visible);
        retrygame.setVisible(visible);
        exitgame.setVisible(visible);
        Player winner = gameMaster.getWinner();
        // set label menang player info to player name
        if (gameMaster.getPlayer(0).getGulden() == gameMaster.getPlayer(1).getGulden()) {
            playDrawMusic();
            menang_player_info.setText("Player 1's gulden is the same as Player 2's!");
        } else {
            playVictoryMusic();
            menang_player_info.setText(winner.getName() + " has win with " + winner.getGulden() + " !");
        }

        glowButtonMaker.setGlow(retrygame);
        retrygame.setOnMouseClicked(event -> {
            gameMaster = new GameMaster();
            initializeGameComponents();
            winning_bg.setVisible(false);
            menang_player_info.setVisible(false);
            retrygame.setVisible(false);
            exitgame.setVisible(false);
        });

        exitgame.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            System.exit(0);
        });
    }

    public void setState(String state) {
        hideAll();
        if (!gameMaster.isBearAttack()) {
            switch (state) {
                case "LoadPlugin":
                    setLoadPluginVisible(true);
                    break;
                case "LoadState":
                    setLoadStateVisible(true);
                    break;
                case "SaveState":
                    setSaveStateVisible(true);
                    break;
                case "TokoState":
                    setTokoStateVisible(true);
                    break;
            }
        }
    }

    private void hideAll() {
        background.setVisible(false);
        close.setVisible(false);
        load_button.setVisible(false);
        load_folder.setVisible(false);
        load_folder_field.setVisible(false);
        load_format.setVisible(false);
        load_format_field.setVisible(false);
        load_title.setVisible(false);
        plugin_button.setVisible(false);
        plugin_file.setVisible(false);
        plugin_file_field.setVisible(false);
        plugin_title.setVisible(false);
        save_button.setVisible(false);
        save_folder.setVisible(false);
        save_folder_field.setVisible(false);
        save_format.setVisible(false);
        save_format_field.setVisible(false);
        save_title.setVisible(false);
        close.setVisible(false);
        chooseFilePluginLabel.setVisible(false);
        saveFormatComboBox.setVisible(false);
        chooseSaveFolderLabel.setVisible(false);
        loadFormatComboBox.setVisible(false);
        chooseLoadFolderLabel.setVisible(false);
        toko_bg.setVisible(false);
        toko_title.setVisible(false);
        toko_deck1.setVisible(false);
        toko_deck2.setVisible(false);
        toko_deck3.setVisible(false);
        toko_deck4.setVisible(false);
        toko_deck5.setVisible(false);
        toko_deck6.setVisible(false);
        toko_deck7.setVisible(false);
        toko_deck8.setVisible(false);
        toko_deck9.setVisible(false);
        toko_deck10.setVisible(false);
        toko_deck11.setVisible(false);
        toko_deck12.setVisible(false);
        toko_back.setVisible(false);
        toko_daging_domba.setVisible(false);
        toko_jagung.setVisible(false);
        toko_daging_kuda.setVisible(false);
        toko_sirip_hiu.setVisible(false);
        toko_stroberi.setVisible(false);
        toko_susu.setVisible(false);
        toko_telur.setVisible(false);
        toko_labu.setVisible(false);
        toko_daging_beruang.setVisible(false);
    }

    private void setTokoStateVisible(boolean visible) {
        toko_bg.setVisible(visible);
        toko_title.setVisible(visible);
        toko_deck1.setVisible(visible);
        toko_deck2.setVisible(visible);
        toko_deck3.setVisible(visible);
        toko_deck4.setVisible(visible);
        toko_deck5.setVisible(visible);
        toko_deck6.setVisible(visible);
        toko_deck7.setVisible(visible);
        toko_deck8.setVisible(visible);
        toko_deck9.setVisible(visible);
        toko_deck10.setVisible(visible);
        toko_deck11.setVisible(visible);
        toko_deck12.setVisible(visible);
        toko_back.setVisible(visible);
        toko_daging_domba.setVisible(visible);
        toko_jagung.setVisible(visible);
        toko_daging_kuda.setVisible(visible);
        toko_sirip_hiu.setVisible(visible);
        toko_stroberi.setVisible(visible);
        toko_susu.setVisible(visible);
        toko_telur.setVisible(visible);
        toko_labu.setVisible(visible);
        toko_daging_beruang.setVisible(visible);

        List<String> deckImageUrls = getActiveDeckImageUrls();
        List<String> deckImageNames = getActiveDeckImageName();
        updateTokoDeckImages(deckImageUrls);

        toko_daging_domba.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            currentProductName = "Daging Domba";
            PopUpToko.setVisible(visible);
            ImageToko.setImage(new Image("/assets/OOP 2/OOP 2/icons/daging_domba.png"));
            ImageToko.setVisible(visible);
            AnimalNameToko.setText("Daging Domba");
            AnimalNameToko.setVisible(visible);
            ClosePopUpToko.setVisible(visible);
            LabelHarga.setVisible(visible);
            Harga.setVisible(visible);
            BuyButton.setVisible(visible);
            LabelKuantitas.setVisible(visible);
            Kuantitas.setVisible(visible);
            Kuantitas.setText((gameMaster.getShop().getStock().get(currentProductName)) + "");
            Harga.setText(gameMaster.getShop().getProducts().get(currentProductName).getPrice() + "");
        });

        toko_jagung.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            currentProductName = "Jagung";
            PopUpToko.setVisible(visible);
            ImageToko.setImage(new Image("/assets/OOP 2/OOP 2/icons/jagung.png"));
            ImageToko.setVisible(visible);
            AnimalNameToko.setText("Jagung");
            AnimalNameToko.setVisible(visible);
            ClosePopUpToko.setVisible(visible);
            LabelHarga.setVisible(visible);
            Harga.setVisible(visible);
            BuyButton.setVisible(visible);
            LabelKuantitas.setVisible(visible);
            Kuantitas.setVisible(visible);
            Kuantitas.setText((gameMaster.getShop().getStock().get(currentProductName)) + "");
            Harga.setText(gameMaster.getShop().getProducts().get(currentProductName).getPrice() + "");
        });

        toko_daging_kuda.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            currentProductName = "Daging Kuda";
            PopUpToko.setVisible(visible);
            ImageToko.setImage(new Image("/assets/OOP 2/OOP 2/icons/daging_kuda.png"));
            ImageToko.setVisible(visible);
            AnimalNameToko.setText("Daging Kuda");
            AnimalNameToko.setVisible(visible);
            ClosePopUpToko.setVisible(visible);
            LabelHarga.setVisible(visible);
            Harga.setVisible(visible);
            BuyButton.setVisible(visible);
            LabelKuantitas.setVisible(visible);
            Kuantitas.setVisible(visible);
            Kuantitas.setText((gameMaster.getShop().getStock().get(currentProductName)) + "");
            Harga.setText(gameMaster.getShop().getProducts().get(currentProductName).getPrice() + "");
        });

        toko_sirip_hiu.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            currentProductName = "Sirip Hiu";
            PopUpToko.setVisible(visible);
            ImageToko.setImage(new Image("/assets/OOP 2/OOP 2/icons/sirip_hiu.png"));
            ImageToko.setVisible(visible);
            AnimalNameToko.setText("Sirip Hiu");
            AnimalNameToko.setVisible(visible);
            ClosePopUpToko.setVisible(visible);
            LabelHarga.setVisible(visible);
            LabelKuantitas.setVisible(visible);
            Kuantitas.setVisible(visible);
            Kuantitas.setText((gameMaster.getShop().getStock().get(currentProductName)) + "");
            Harga.setVisible(visible);
            BuyButton.setVisible(visible);
            Harga.setText(gameMaster.getShop().getProducts().get(currentProductName).getPrice() + "");
        });

        toko_stroberi.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            currentProductName = "Stroberi";
            PopUpToko.setVisible(visible);
            ImageToko.setImage(new Image("/assets/OOP 2/OOP 2/icons/stroberi.png"));
            ImageToko.setVisible(visible);
            AnimalNameToko.setText("Stroberi");
            AnimalNameToko.setVisible(visible);
            ClosePopUpToko.setVisible(visible);
            LabelHarga.setVisible(visible);
            Harga.setVisible(visible);
            BuyButton.setVisible(visible);
            LabelKuantitas.setVisible(visible);
            Kuantitas.setVisible(visible);
            Kuantitas.setText((gameMaster.getShop().getStock().get(currentProductName)) + "");
            Harga.setText(gameMaster.getShop().getProducts().get(currentProductName).getPrice() + "");
        });

        toko_susu.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            currentProductName = "Susu";
            PopUpToko.setVisible(visible);
            ImageToko.setImage(new Image("/assets/OOP 2/OOP 2/icons/susu.png"));
            ImageToko.setVisible(visible);
            AnimalNameToko.setText("Susu");
            AnimalNameToko.setVisible(visible);
            ClosePopUpToko.setVisible(visible);
            LabelHarga.setVisible(visible);
            Harga.setVisible(visible);
            BuyButton.setVisible(visible);
            LabelKuantitas.setVisible(visible);
            Kuantitas.setVisible(visible);
            Kuantitas.setText((gameMaster.getShop().getStock().get(currentProductName)) + "");
            Harga.setText(gameMaster.getShop().getProducts().get(currentProductName).getPrice() + "");
        });

        toko_telur.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            currentProductName = "Telur";
            PopUpToko.setVisible(visible);
            ImageToko.setImage(new Image("/assets/OOP 2/OOP 2/icons/telur.png"));
            ImageToko.setVisible(visible);
            AnimalNameToko.setText("Telur");
            AnimalNameToko.setVisible(visible);
            ClosePopUpToko.setVisible(visible);
            LabelHarga.setVisible(visible);
            Harga.setVisible(visible);
            BuyButton.setVisible(visible);
            LabelKuantitas.setVisible(visible);
            Kuantitas.setVisible(visible);
            Kuantitas.setText((gameMaster.getShop().getStock().get(currentProductName)) + "");
            Harga.setText(gameMaster.getShop().getProducts().get(currentProductName).getPrice() + "");
        });

        toko_labu.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            currentProductName = "Labu";
            PopUpToko.setVisible(visible);
            ImageToko.setImage(new Image("/assets/OOP 2/OOP 2/icons/labu.png"));
            ImageToko.setVisible(visible);
            AnimalNameToko.setText("Labu");
            AnimalNameToko.setVisible(visible);
            ClosePopUpToko.setVisible(visible);
            LabelHarga.setVisible(visible);
            Harga.setVisible(visible);
            BuyButton.setVisible(visible);
            LabelKuantitas.setVisible(visible);
            Kuantitas.setVisible(visible);
            Kuantitas.setText((gameMaster.getShop().getStock().get(currentProductName)) + "");
            Harga.setText(gameMaster.getShop().getProducts().get(currentProductName).getPrice() + "");
        });

        toko_daging_beruang.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            currentProductName = "Daging Beruang";
            PopUpToko.setVisible(visible);
            ImageToko.setImage(new Image("/assets/OOP 2/OOP 2/icons/daging_beruang.png"));
            ImageToko.setVisible(visible);
            AnimalNameToko.setText("Daging Beruang");
            AnimalNameToko.setVisible(visible);
            ClosePopUpToko.setVisible(visible);
            LabelHarga.setVisible(visible);
            Harga.setVisible(visible);
            BuyButton.setVisible(visible);
            LabelKuantitas.setVisible(visible);
            Kuantitas.setVisible(visible);
            Kuantitas.setText((gameMaster.getShop().getStock().get(currentProductName)) + "");
            Harga.setText(gameMaster.getShop().getProducts().get(currentProductName).getPrice() + "");
        });

        toko_deck7.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            currentProduct = 0;
            PopUpToko.setVisible(visible);
            ImageToko.setImage(new Image(deckImageUrls.get(0)));
            ImageToko.setVisible(visible);
            AnimalNameToko.setText(deckImageNames.get(0));
            AnimalNameToko.setVisible(visible);
            ClosePopUpToko.setVisible(visible);
            LabelHarga.setVisible(visible);
            Harga.setVisible(visible);
            SellButton.setVisible(visible);
            Card currentCard = gameMaster.getCurrentPlayer().getCardActiveDeck(currentProduct);
            if (currentCard instanceof Product) {
                Harga.setText(((Product) currentCard).getPrice() + "");
            } else {
                Harga.setText("Nan");
            }
        });

        toko_deck8.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            currentProduct = 1;
            PopUpToko.setVisible(visible);
            ImageToko.setImage(new Image(deckImageUrls.get(1)));
            ImageToko.setVisible(visible);
            AnimalNameToko.setText(deckImageNames.get(1));

            AnimalNameToko.setVisible(visible);
            ClosePopUpToko.setVisible(visible);
            LabelHarga.setVisible(visible);
            Harga.setVisible(visible);
            SellButton.setVisible(visible);
            Card currentCard = gameMaster.getCurrentPlayer().getCardActiveDeck(currentProduct);
            if (currentCard instanceof Product) {
                Harga.setText(((Product) currentCard).getPrice() + "");
            } else {
                Harga.setText("Nan");
            }
        });

        toko_deck9.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            currentProduct = 2;
            PopUpToko.setVisible(visible);
            ImageToko.setImage(new Image(deckImageUrls.get(2)));
            ImageToko.setVisible(visible);
            AnimalNameToko.setText(deckImageNames.get(2));
            AnimalNameToko.setVisible(visible);
            ClosePopUpToko.setVisible(visible);
            LabelHarga.setVisible(visible);
            Harga.setVisible(visible);
            SellButton.setVisible(visible);
            Card currentCard = gameMaster.getCurrentPlayer().getCardActiveDeck(currentProduct);
            if (currentCard instanceof Product) {
                Harga.setText(((Product) currentCard).getPrice() + "");
            } else {
                Harga.setText("Nan");
            }
        });

        toko_deck10.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            currentProduct = 3;
            PopUpToko.setVisible(visible);
            ImageToko.setImage(new Image(deckImageUrls.get(3)));
            ImageToko.setVisible(visible);
            AnimalNameToko.setText(deckImageNames.get(3));
            AnimalNameToko.setVisible(visible);
            ClosePopUpToko.setVisible(visible);
            LabelHarga.setVisible(visible);
            Harga.setVisible(visible);
            SellButton.setVisible(visible);
            Card currentCard = gameMaster.getCurrentPlayer().getCardActiveDeck(currentProduct);
            if (currentCard instanceof Product) {
                Harga.setText(((Product) currentCard).getPrice() + "");
            } else {
                Harga.setText("Nan");
            }
        });

        toko_deck11.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            currentProduct = 4;
            PopUpToko.setVisible(visible);
            ImageToko.setImage(new Image(deckImageUrls.get(4)));
            ImageToko.setVisible(visible);
            AnimalNameToko.setText(deckImageNames.get(4));
            AnimalNameToko.setVisible(visible);
            ClosePopUpToko.setVisible(visible);
            LabelHarga.setVisible(visible);
            Harga.setVisible(visible);
            SellButton.setVisible(visible);
            Card currentCard = gameMaster.getCurrentPlayer().getCardActiveDeck(currentProduct);
            if (currentCard instanceof Product) {
                Harga.setText(((Product) currentCard).getPrice() + "");
            } else {
                Harga.setText("Nan");
            }
        });

        toko_deck12.setOnMouseClicked(event -> {
            clickMediaPlayer.stop();
            clickMediaPlayer.play();
            currentProduct = 5;
            PopUpToko.setVisible(visible);
            ImageToko.setImage(new Image(deckImageUrls.get(5)));
            ImageToko.setVisible(visible);
            AnimalNameToko.setText(deckImageNames.get(5));
            AnimalNameToko.setVisible(visible);
            ClosePopUpToko.setVisible(visible);
            LabelHarga.setVisible(visible);
            Harga.setVisible(visible);
            SellButton.setVisible(visible);
            Card currentCard = gameMaster.getCurrentPlayer().getCardActiveDeck(currentProduct);
            if (currentCard instanceof Product) {
                Harga.setText(((Product) currentCard).getPrice() + "");
            } else {
                Harga.setText("Nan");
            }
        });

        SellButton.setOnMouseClicked(event -> {
            moneyMediaPlayer.stop();
            moneyMediaPlayer.play();
            try {
                gameMaster.getShop().Sell(gameMaster.getCurrentPlayer(), currentProduct);
                loadToko();
                PopUpToko.setVisible(false);
                ImageToko.setVisible(false);
                AnimalNameToko.setVisible(false);
                ClosePopUpToko.setVisible(false);
                Harga.setVisible(false);
                BuyButton.setVisible(false);
                SellButton.setVisible(false);
                LabelHarga.setVisible(false);
                LabelKuantitas.setVisible(false);
                Kuantitas.setVisible(false);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        BuyButton.setOnMouseClicked(event -> {
            moneyMediaPlayer.stop();
            moneyMediaPlayer.play();
            try {
                gameMaster.getShop().Buy(gameMaster.getCurrentPlayer(), currentProductName);
                loadToko();
                PopUpToko.setVisible(false);
                ImageToko.setVisible(false);
                AnimalNameToko.setVisible(false);
                ClosePopUpToko.setVisible(false);
                Harga.setVisible(false);
                BuyButton.setVisible(false);
                SellButton.setVisible(false);
                LabelHarga.setVisible(false);
                LabelKuantitas.setVisible(false);
                Kuantitas.setVisible(false);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    private void setLoadPluginVisible(boolean visible) {
        background.setVisible(visible);
        plugin_button.setVisible(visible);
        plugin_file.setVisible(visible);
        plugin_file_field.setVisible(visible);
        plugin_title.setVisible(visible);
        close.setVisible(visible);
        chooseFilePluginLabel.setVisible(visible);
    }

    private void setLoadStateVisible(boolean visible) {
        background.setVisible(visible);
        load_button.setVisible(visible);
        load_folder.setVisible(visible);
        load_folder_field.setVisible(visible);
        load_format.setVisible(visible);
        load_format_field.setVisible(visible);
        load_title.setVisible(visible);
        close.setVisible(visible);
        loadFormatComboBox.setVisible(visible);
        chooseLoadFolderLabel.setVisible(visible);
    }

    private void setSaveStateVisible(boolean visible) {
        background.setVisible(visible);
        save_button.setVisible(visible);
        save_folder.setVisible(visible);
        save_folder_field.setVisible(visible);
        save_format.setVisible(visible);
        save_format_field.setVisible(visible);
        save_title.setVisible(visible);
        close.setVisible(visible);
        saveFormatComboBox.setVisible(visible);
        chooseSaveFolderLabel.setVisible(visible);
    }

    @FXML
    private void handleFileChoose() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Plugin File");

        // Filter jar files
        FileChooser.ExtensionFilter jarFilter = new FileChooser.ExtensionFilter("JAR files (*.jar)", "*.jar");
        fileChooser.getExtensionFilters().addAll(jarFilter);

        Stage stage = (Stage) chooseFilePluginLabel.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            chooseFilePluginLabel.setText(file.getAbsolutePath());
        }
    }

    @FXML
    private void handleSaveFolderChoose() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose Folder");

        Stage stage = (Stage) chooseSaveFolderLabel.getScene().getWindow();
        File selectedDirectory = directoryChooser.showDialog(stage);
        if (selectedDirectory != null) {
            chooseSaveFolderLabel.setText(selectedDirectory.getAbsolutePath());
        }
    }

    private void handleLoadFolderChoose() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose Folder");

        Stage stage = (Stage) chooseSaveFolderLabel.getScene().getWindow();
        File selectedDirectory = directoryChooser.showDialog(stage);
        if (selectedDirectory != null) {
            chooseLoadFolderLabel.setText(selectedDirectory.getAbsolutePath());
        }
    }

    @FXML
    private void handlePlugin() {
        String pluginFileName = chooseFilePluginLabel.getText();
        if (pluginFileName == null || pluginFileName.isEmpty()) {
            // TO-DO: Handle error - no file chosen
            return;
        }

        File file = new File(pluginFileName);
        if (!file.exists()) {
            // TO-DO: Handle error: file does not exist
            return;
        }

        try {
            // Load the plugin
            PluginLoader pluginLoader = new PluginLoader();

            pluginLoader.loadPlugin(file.getAbsolutePath(), gameMaster.getSaveLoad());

            // Update the format combo boxes
            saveFormatComboBox.getItems().clear();
            loadFormatComboBox.getItems().clear();
            saveFormatComboBox.getItems().addAll("txt");
            loadFormatComboBox.getItems().addAll("txt");
            for (PluginInterface plugin : gameMaster.getSaveLoad().getSaveLoaders()) {
                String type = plugin.getType();

                if (!saveFormatComboBox.getItems().contains(type)) {
                    saveFormatComboBox.getItems().add(type);
                    loadFormatComboBox.getItems().add(type);
                }
            }
            chooseFilePluginLabel.setText("Click here to choose file!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSave() {
        String format = saveFormatComboBox.getValue();
        if (format == null || format.isEmpty()) {
            // TO-DO: handle error
            return;
        }
        // save operation
        try {
            String foldername = chooseSaveFolderLabel.getText();
            gameMaster.save(foldername, format);
            saveFormatComboBox.setValue(null);
            chooseSaveFolderLabel.setText("Click here to choose folder!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLoad() {
        String format = loadFormatComboBox.getValue();
        if (format == null || format.isEmpty()) {
            // TO-DO: Handle the error, format not selected
            return;
        }
        // load
        try {
            String foldername = chooseLoadFolderLabel.getText();
            gameMaster.load(foldername, format);
            gameMaster.getCurrentPlayer().printGridActiveDeckTest();
            loadGridActiveDeck();
            loadOther();
            loadFormatComboBox.setValue(null);
            chooseLoadFolderLabel.setText("Click here to choose folder!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTokoDeckImages(List<String> imageUrls) {
        ImageView[] tokoDecks = {
                toko_deck7, toko_deck8, toko_deck9,
                toko_deck10, toko_deck11, toko_deck12
        };

        for (int i = 0; i < imageUrls.size() && i < tokoDecks.length; i++) {
            // handle if null
            if (imageUrls.get(i) == null) {
                tokoDecks[i].setImage(null);
            } else {
                tokoDecks[i].setImage(new Image(imageUrls.get(i)));
            }
        }
    }

    public void loadGridActiveDeck() {
        try {
            for (ImageView activeCard : listActiveDeck) {
                String activeCardId = activeCard.getId();
                int index = ((int) activeCardId.charAt(activeCardId.length() - 1) - '0') - 1;
                String imageUrl = gameMaster.getCurrentPlayer().getCardActiveDeck(index).getPathToImg();
                if (imageUrl == null) {
                    activeCard.setImage(null);
                } else {
                    activeCard.setImage(new Image(imageUrl));
                }
            }

            for (ImageView[] listGrid : matrixCardInField) {
                for (ImageView grid : listGrid) {
                    String gridId = grid.getId();
                    int row = (int) gridId.charAt(gridId.length() - 2) - '0';
                    int col = (int) gridId.charAt(gridId.length() - 1) - '0';
                    String imageUrl = gameMaster.getCurrentFieldPlayer().getCardGrid(row - 1, col - 1).getPathToImg();
                    if (imageUrl == null) {
                        grid.setImage(null);
                    } else {
                        grid.setImage(new Image(imageUrl));
                        draggableMaker.makeDraggable(grid, matrixGrid, gameMaster, true);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception, e.g., show an error message to the user
        }
    }

    public void loadOther() {
        firstPlayerMoney.setText(gameMaster.getPlayer(0).getGulden() + "");
        secondPlayerMoney.setText(gameMaster.getPlayer(1).getGulden() + "");
        turn.setText((gameMaster.getCurrentTurn() + 1) + "");
        if (gameMaster.getCurrentTurn() % 2 == 1) {
            titleplayer1turn.setVisible(true);
            titleplayer2turn.setVisible(false);
        } else {
            titleplayer1turn.setVisible(false);
            titleplayer2turn.setVisible(true);
        }
        gameMaster.setCurrentFieldPlayer(gameMaster.getCurrentPlayer());
        toLadangku1.setVisible(true);
        toLadangLawan1.setVisible(false);
        toToko1.setVisible(false);
        SaveState1.setVisible(false);
        LoadPlugin1.setVisible(false);
        LoadState1.setVisible(false);
    }

    public void loadToko() {
        int enumerator = 0;
        List<String> updatedActiveDeck = getActiveDeckImageUrls();
        updateTokoDeckImages(updatedActiveDeck);
    }

    public void setAllLabel(int row, int col) {
        try {
            AnimalName.setText(gameMaster.getCurrentPlayer().getCardGrid(row, col).getName());
            String weightDetail = gameMaster.getCurrentPlayer().getCardGrid(row, col).getWeight() + " ("
                    + gameMaster.getCurrentPlayer().getCardGrid(row, col).getWeightAfterEffect() + ")";
            label2.setText(weightDetail);
            List<Item> listItems = gameMaster.getCurrentPlayer().getCardGrid(row, col).getItemEffects();
            if (listItems.size() == 0) {
                label4.setText("");
                label5.setText("");
            }

            List<String> effect = gameMaster.getCurrentPlayer().getAllEffecArrayList(row, col);
            if (listItems.size() >= 1) {
                String temp = "";
                for (String str : effect) {
                    temp += str;
                }
                label4.setText(temp);

            }

        } catch (BaseException e) {
            e.printStackTrace();
            // Handle the exception, e.g., show an error message to the user
        }
    }

    public ImageView getImageViewById(String id) {
        switch (id) {
            case "kosong11":
                return kosong11;
            case "kosong12":
                return kosong12;
            case "kosong13":
                return kosong13;
            case "kosong14":
                return kosong14;
            case "kosong15":
                return kosong15;
            case "kosong21":
                return kosong21;
            case "kosong22":
                return kosong22;
            case "kosong23":
                return kosong23;
            case "kosong24":
                return kosong24;
            case "kosong25":
                return kosong25;
            case "kosong31":
                return kosong31;
            case "kosong32":
                return kosong32;
            case "kosong33":
                return kosong33;
            case "kosong34":
                return kosong34;
            case "kosong35":
                return kosong35;
            case "kosong41":
                return kosong41;
            case "kosong42":
                return kosong42;
            case "kosong43":
                return kosong43;
            case "kosong44":
                return kosong44;
            case "kosong45":
                return kosong45;
            default:
                return null;
        }
    }

    @FXML
    public Integer[] simulateBearAttack() {
        Pane[][] matrix_pane = new Pane[][] {
                { plane11, plane12, plane13, plane14, plane15 },
                { plane21, plane22, plane23, plane24, plane25 },
                { plane31, plane32, plane33, plane34, plane35 },
                { plane41, plane42, plane43, plane44, plane45 }
        };

        for (Pane[] row : matrix_pane) {
            for (Pane pane : row) {
                if (pane == null) {
                    System.err.println("Pane is null in matrix_pane");
                }
            }
        }

        // clock.setVisible(true);
        // startCountdown();
        return draggableMaker.setRedGlowOnRandomGroup(matrix_pane);
    }

    public void setPanenPageVisibility(boolean bool, boolean isHarvestable) {
        label1.setVisible(bool);
        label2.setVisible(bool);
        label3.setVisible(bool);
        label4.setVisible(bool);
        label5.setVisible(bool);
        ContainerPanen.setVisible(bool);
        CloseBtn.setVisible(bool);
        AnimalImage.setVisible(bool);
        AnimalName.setVisible(bool);
        if (isHarvestable) {
            PanenBtn.setVisible(bool);
        } else {
            PanenBtn.setVisible(!bool);
        }

    }

    public ImageView getAnimalImage() {
        return AnimalImage;
    }

    public ImageView getPanenBtn() {
        return PanenBtn;
    }

    public boolean isInEnemyField() {
        return toLadangLawan1.isVisible();
    }

    private List<String> getActiveDeckImageUrls() {
        List<String> imageUrls = new ArrayList<>();

        try {
            for (ImageView activeCard : listActiveDeck) {
                String activeCardId = activeCard.getId();
                int index = ((int) activeCardId.charAt(activeCardId.length() - 1) - '0') - 1;
                String imageUrl = gameMaster.getCurrentPlayer().getCardActiveDeck(index).getPathToImg();
                if (imageUrl == null) {
                    imageUrls.add(null);
                } else {
                    imageUrls.add(imageUrl);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            // Handle the exception, e.g., show an error message to the user
        }
        return imageUrls;
    }

    private List<String> getActiveDeckImageName() {
        List<String> imageNames = new ArrayList<>();

        try {
            for (ImageView activeCard : listActiveDeck) {
                String activeCardId = activeCard.getId();
                int index = ((int) activeCardId.charAt(activeCardId.length() - 1) - '0') - 1;
                String imageName = gameMaster.getCurrentPlayer().getCardActiveDeck(index).getName();
                if (imageName == null) {
                    imageNames.add("Tidak ada kartu disini!");
                } else {
                    imageNames.add(imageName);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            // Handle the exception, e.g., show an error message to the user
        }
        return imageNames;

    }

    private Image[] loadGifFrames(String filePath) {
        // Implement loading GIF frames from file here
        // For simplicity, I'm just returning an array with a single frame
        try {
            Image frame = new Image(new FileInputStream(filePath));
            return new Image[]{frame};
        } catch (Exception e) {
            return new Image[0];
        }
    }

    public void playGif(String pathImage, ImageView grid) {
        try { 
            Image[] frames = loadGifFrames(pathImage);
            int[] frameIndex = {0}; // Use an array to hold the frame index
            ImageView imageView = new ImageView(frames[frameIndex[0]]);
            imageView.setLayoutX(grid.getLayoutX());
            imageView.setLayoutY(grid.getLayoutY());
    
            // Add the ImageView to your scene (assuming you have a Pane to add it to)
            ((Pane) grid.getParent()).getChildren().add(imageView);
    
            // Create a Timeline to animate the frames
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
                frameIndex[0] = (frameIndex[0] + 1) % frames.length;
                imageView.setImage(frames[frameIndex[0]]);
            }));
            timeline.play();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
 
    public void placeMusicOn() {
        placeMediaPlayer.stop();
        placeMediaPlayer.play();
    }

    public void playVictoryMusic() {
        mainMediaPlayer.stop();
        String musicFile = "/assets/OOP 2/OOP 2/music/victoryMusic.mp3";
        URL musicUrl = getClass().getResource(musicFile);
        if (musicUrl != null) {
            Media media = new Media(musicUrl.toExternalForm());
            mainMediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mainMediaPlayer);
        } else {
            System.out.println("Music file not found");
        }
        mainMediaPlayer.setOnPlaying(() -> {
            mainMediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.toSeconds() >= 30) {
                    mainMediaPlayer.seek(Duration.ZERO);
                }
            });
        });
        mainMediaPlayer.play();
    }
    
    public void playDrawMusic() {
        mainMediaPlayer.stop();
        String musicFile = "/assets/OOP 2/OOP 2/music/drawMusic.mp3";
        URL musicUrl = getClass().getResource(musicFile);
        if (musicUrl != null) {
            Media media = new Media(musicUrl.toExternalForm());
            mainMediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mainMediaPlayer);
        } else {
            System.out.println("Music file not found");
        }
        mainMediaPlayer.setOnPlaying(() -> {
            mainMediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.toSeconds() >= 5) {
                    mainMediaPlayer.seek(Duration.ZERO);
                }
            });
        });
        mainMediaPlayer.play();
    }
}
// // Create an ImageView to display the frames
// imageView = new ImageView(frames[frameIndex]);

// // Create a Timeline to animate the frames
// timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
//     frameIndex = (frameIndex + 1) % frames.length;
//     imageView.setImage(frames[frameIndex]);
// }));     