package me.mrgeneralq.sleepmost.commands.dream;

import me.mrgeneralq.sleepmost.enums.DreamType;
import me.mrgeneralq.sleepmost.interfaces.IDreamService;
import me.mrgeneralq.sleepmost.interfaces.IMessageService;
import me.mrgeneralq.sleepmost.interfaces.ISubCommand;
import me.mrgeneralq.sleepmost.models.Dream;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class DreamCreateCommand implements ISubCommand {
    private final IDreamService dreamService;
    private final IMessageService messageService;

    public DreamCreateCommand(IDreamService dreamService, IMessageService messageService) {
        this.dreamService = dreamService;
        this.messageService = messageService;
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        //ARS <dreamName> [type]

        if(args.length == 0){
            //MSG specify a dream
            return true;
        }

        String dreamName = args[0];


        if(this.dreamService.dreamExists(dreamName)){
            this.messageService.sendMessage(sender,
                    this.messageService.getMessagePrefixed("&cThis dream already exists").build());
            return true;
        }

        DreamType dreamType = DreamType.NORMAL;

        if(args.length > 1){
                try{
                    dreamType = DreamType.valueOf(args[2]);
                }catch (Exception ex){
                    Bukkit.getLogger().warning("Invalid dream type. Type has been set to NORMAL");
                }
        }

        Dream dream = new Dream(dreamName, dreamType);
        dreamService.createDream(dream);

        //todo message
        this.messageService.sendMessage(sender, messageService.getMessagePrefixed("&eNew dream of type &f%dream-type% &ecreated!")
                .setDream(dream)
                .build());
        return true;
    }
}
