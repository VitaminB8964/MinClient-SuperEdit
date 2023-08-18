package net.minecraft.client.gui;

import net.minecraft.util.IProgressUpdate;
import net.optifine.CustomLoadingScreen;
import net.optifine.CustomLoadingScreens;

public class GuiScreenWorking extends GuiScreen implements IProgressUpdate
{
    private String title = "";
    private String stage = "";
    private int progress;
    private boolean doneWorking;
    private final CustomLoadingScreen customLoadingScreen = CustomLoadingScreens.getCustomLoadingScreen();

    /**
     * Shows the 'Saving level' string.
     */
    public void displaySavingString(String message)
    {
        this.resetProgressAndMessage(message);
    }

    /**
     * this string, followed by "working..." and then the "% complete" are the 3 lines shown. This resets progress to 0,
     * and the WorkingString to "working...".
     */
    public void resetProgressAndMessage(String message)
    {
        this.title = message;
        this.displayLoadingString("Working...");
    }

    /**
     * Displays a string on the loading screen supposed to indicate what is being done currently.
     */
    public void displayLoadingString(String message)
    {
        this.stage = message;
        this.setLoadingProgress(0);
    }

    /**
     * Updates the progress bar on the loading screen to the specified amount.
     */
    public void setLoadingProgress(int progress)
    {
        this.progress = progress;
    }

    public void setDoneWorking()
    {
        this.doneWorking = true;
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        if (this.doneWorking)
        {
            this.mc.displayGuiScreen(null);
        }
        else
        {
            if (this.customLoadingScreen != null && this.mc.world == null)
            {
                this.customLoadingScreen.drawBackground(this.width, this.height);
            }
            else
            {
                this.drawDefaultBackground();
            }

            if (this.progress > 0)
            {
                this.drawCenteredString(this.fontRenderer, this.title, this.width / 2, 70, 16777215);
                this.drawCenteredString(this.fontRenderer, this.stage + " " + this.progress + "%", this.width / 2, 90, 16777215);
            }

            super.drawScreen(mouseX, mouseY, partialTicks);
        }
    }
}