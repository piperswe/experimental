package me.piperswe.utils.gui;

import javafx.scene.Parent;
import lombok.NonNull;

public abstract class ReactiveComponent<State, SelectedState> extends StaticComponent<State> implements Selector<State, SelectedState>, StateListener<SelectedState> {
    public ReactiveComponent(@NonNull StateContainer<State> stateContainer) {
        super(stateContainer);
        stateContainer.listen(new SelectedStateListener<>(this, new MemoizedSelector<>(this)));
    }

    @Override
    public Parent mount() {
        var node = render();
        var selected = select(getStateContainer().get());
        handleStateChange(selected, selected);
        return node;
    }
}
