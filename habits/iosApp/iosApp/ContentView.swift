import shared
import SwiftUI

class AppViewModel: ObservableObject {
  let stopwatchViewModel : StopwatchViewModel = StopwatchViewModel()
  @Published var stopwatchState : String = ""
     var closeable : Closeable? = nil
     
  init() {
      self.closeable = StopwatchViewModelKt.watch(stopwatchViewModel.stopwatchFlow, block: { newValue in
          let value = newValue as! String
          print(value)
          self.stopwatchState = value
      })
   }
}

struct ContentView: View {
    let greet = Greeting().greeting()
    @ObservedObject var appViewModel = AppViewModel()

    var body: some View {
        Text("Counter: \(appViewModel.stopwatchState)")
            .onAppear {
                print("appear")
                appViewModel.stopwatchViewModel.start()
            }
            .onDisappear {
                print("disappear")
                // TODO: dig in further how to correctly sync SwiftUI + Coroutines lifecycles
                appViewModel.stopwatchViewModel.dispose()
                appViewModel.closeable?.close()
            }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
