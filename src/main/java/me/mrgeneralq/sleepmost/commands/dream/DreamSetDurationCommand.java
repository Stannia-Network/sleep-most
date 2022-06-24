package me.mrgeneralq.sleepmost.commands.dream;

import me.mrgeneralq.sleepmost.interfaces.IDreamService;
import me.mrgeneralq.sleepmost.interfaces.IMessageService;
import me.mrgeneralq.sleepmost.interfaces.ISubCommand;
import me.mrgeneralq.sleepmost.models.Dream;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class DreamSetDurationCommand implements ISubCommand {

    private final static Integer DEFAULT_DURATION = -1;

    private final IDreamService dreamService;
    private final IMessageService messageService;


    public DreamSetDurationCommand(IDreamService dreamService, IMessageService messageService) {
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

        if(!this.dreamService.dreamExists(dreamName)){
            this.messageService.sendMessage(sender,
                    this.messageService.getMessagePrefixed("&cThis dream does not exist!").build());
            return true;
        }

        Dream dream = dreamService.getDream(dreamName);

        if(args.length < 2){
           this.messageService.sendMessage(sender,
                   this.messageService.getMessagePrefixed("&cMissing duration value. Please provide the amount of seconds.").build());
           return true;
        }

        String durationString = args[1];
        int duration = DEFAULT_DURATION;

        try{

            duration = Integer.parseInt(durationString);
        }catch (Exception e){
            this.messageService.sendMessage(sender,
                    this.messageService.getMessagePrefixed("&cInvalid format! Please provide a numeric value").build());
            return true;
        }
        //todo check if chance should be changed to minimum 0 and max 100
        dream.setDuration(duration);
        dreamService.updateDream(dream);

        //todo message
        this.messageService.sendMessage(sender, messageService.getMessagePrefixed("&eThe duration has been updated to %duration%!")
                .setDream(dream)
                .build());
        return true;
    }
}
