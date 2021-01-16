package me.mrgeneralq.sleepmost.repositories;

import me.mrgeneralq.sleepmost.flags.*;
import me.mrgeneralq.sleepmost.flags.controllers.ConfigFlagController;
import me.mrgeneralq.sleepmost.flags.types.AbstractFlag;
import me.mrgeneralq.sleepmost.interfaces.*;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class FlagsRepository implements IFlagsRepository
{
    private final Map<String, ISleepFlag<?>> flagByName = new HashMap<>();

    private final IConfigRepository configRepository;

    //all flags objects(for type safety)
    private PercentageRequiredFlag percentageRequiredFlag;
    private MobNoTargetFlag mobNoTargetFlag;
    private UseExemptFlag useExemptFlag;
    private PreventSleepFlag preventSleepFlag;
    private PreventPhantomFlag preventPhantomFlag;
    private NightcycleAnimationFlag nightcycleAnimationFlag;
    private StormSleepFlag stormSleepFlag;
    private UseAfkFlag useAfkFlag;
    private CalculationMethodFlag calculationMethodFlag;
    private PlayersRequiredFlag playersRequiredFlag;
    //private TestFlag testFlag;

    public FlagsRepository(IConfigRepository configRepository)
    {
        this.configRepository = configRepository;

        //setupFlag(this.testFlag = new TestFlag(new ConfigFlagController<>(this.configRepository)));
        setupFlag(this.nightcycleAnimationFlag = new NightcycleAnimationFlag(new ConfigFlagController<>(this.configRepository)));
        setupFlag(this.mobNoTargetFlag = new MobNoTargetFlag(new ConfigFlagController<>(this.configRepository)));
        setupFlag(this.useExemptFlag = new UseExemptFlag(new ConfigFlagController<>(this.configRepository)));
        setupFlag(this.preventSleepFlag = new PreventSleepFlag(new ConfigFlagController<>(this.configRepository)));
        setupFlag(this.preventPhantomFlag = new PreventPhantomFlag(new ConfigFlagController<>(this.configRepository)));
        setupFlag(this.stormSleepFlag = new StormSleepFlag(new ConfigFlagController<>(this.configRepository)));
        setupFlag(this.useAfkFlag = new UseAfkFlag(new ConfigFlagController<>(this.configRepository)));
        setupFlag(this.playersRequiredFlag = new PlayersRequiredFlag(new ConfigFlagController<>(this.configRepository)));
        setupFlag(this.percentageRequiredFlag = new PercentageRequiredFlag(new ConfigFlagController<>(this.configRepository)));
        setupFlag(this.calculationMethodFlag = new CalculationMethodFlag(new ConfigFlagController<>(this.configRepository)));
    }

    @Override
    public ISleepFlag<?> getFlag(String flagName)
    {
        return this.flagByName.get(flagName);
    }

    @Override
    public boolean flagExists(String flagName)
    {
        return this.flagByName.containsKey(flagName);
    }

    @Override
    public Set<ISleepFlag<?>> getFlags()
    {
        return new HashSet<>(this.flagByName.values());
    }

    @Override
    public List<String> getFlagsNames(){
        return flagByName.values().stream()
                .map(ISleepFlag::getName)
                .sorted()
                .collect(toList());
    }

    //Flags Getters
    @Override public PercentageRequiredFlag getPercentageRequiredFlag(){
        return this.percentageRequiredFlag;
    }

    @Override public MobNoTargetFlag getMobNoTargetFlag(){
        return this.mobNoTargetFlag;
    }

    @Override public UseExemptFlag getUseExemptFlag() {
        return this.useExemptFlag;
    }

    @Override public PreventSleepFlag getPreventSleepFlag() {
        return this.preventSleepFlag;
    }

    @Override public PreventPhantomFlag getPreventPhantomFlag() {
        return this.preventPhantomFlag;
    }

    @Override public NightcycleAnimationFlag getNightcycleAnimationFlag() {
        return this.nightcycleAnimationFlag;
    }

    @Override public StormSleepFlag getStormSleepFlag() {
        return this.stormSleepFlag;
    }

    @Override public UseAfkFlag getUseAfkFlag() {
        return this.useAfkFlag;
    }

    @Override public CalculationMethodFlag getCalculationMethodFlag() {
        return this.calculationMethodFlag;
    }

    @Override public PlayersRequiredFlag getPlayersRequiredFlag() {
        return this.playersRequiredFlag;
    }

    /*@Override public TestFlag getTestFlag(){
        return this.testFlag;
    }*/

    private <V> void setupFlag(ISleepFlag<V> flag) {
        //register the flag
        this.flagByName.put(flag.getName(), flag);

        //update the controller about which flag it controls
        if(flag instanceof AbstractFlag)
        {
            ((AbstractFlag<V>) flag).getController().setFlag(flag);
        }
    }
}