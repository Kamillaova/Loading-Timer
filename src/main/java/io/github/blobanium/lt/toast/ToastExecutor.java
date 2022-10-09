package io.github.blobanium.lt.toast;

import io.github.blobanium.lt.LoadingTimer;
import io.github.blobanium.lt.resource.ResourceLoadingTimer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableTextContent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ToastExecutor {
    private static String easterEggTranslatable;
    private static final boolean lazydfu = FabricLoader.getInstance().isModLoaded("lazydfu");
    private static final boolean smoothboot = FabricLoader.getInstance().isModLoaded("smoothboot");
    private static final boolean dashloader = FabricLoader.getInstance().isModLoaded("dashloader");
    public static final Logger LOGGER = LogManager.getLogger("Loading Timer");

    public static void executeToast(String translatableDescription, double toastTimeValue) {
        easterEggLaodingTimer();
        easterEggMods();
        easterEggMineclub();
        SystemToast toast = SystemToast.create(MinecraftClient.getInstance(), SystemToast.Type.TUTORIAL_HINT, MutableText.of(new TranslatableTextContent(easterEggTranslatable)), MutableText.of(new TranslatableTextContent(translatableDescription, toastTimeValue)));
        MinecraftClient.getInstance().getToastManager().add(toast);
    }

    private static void easterEggLaodingTimer() {
        short min = 1;
        short max = 1000;
        long randomDouble = Math.round(Math.random() * (max - min + 1) + min);
        if (randomDouble == 100) {
            easterEggTranslatable = "loading-timer.easteregg.title";
            LOGGER.info("Showing Easter egg!");
        } else {
            easterEggTranslatable = "loading-timer.title";
        }
    }

    private static void easterEggMods() {
        if (lazydfu && smoothboot && dashloader) {
            if (LoadingTimer.rawLoadingTime < 3) {
                easterEggTranslatable = "loading-timer.easteregg.insane";
            } else {
                LOGGER.info("Insanity Approaches..");
            }
        }
    }

    private static void easterEggMineclub() {
        if (!ResourceLoadingTimer.resourcesLoaded) {
            try {
                if (MinecraftClient.getInstance().getCurrentServerEntry().address.equals("play.mineclub.com")) {
                    easterEggTranslatable = "loading-timer.easteregg.mineclub";
                }
            } catch (NullPointerException e) {
                LOGGER.debug("Suppressing Null warning");
            }
        }
    }
}
