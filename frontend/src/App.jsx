import EarthquakeTable from "./ui/components/EarthquakeTable";
import EarthquakeMap from "./ui/components/EarthquakeMap";
import useEarthquakes from "./hooks/useEarthquakes.js";
function App() {

    const {earthquakes, loading, error} = useEarthquakes();

    if (loading) return <p>Loading</p>
    if (error) return <p>Error</p>
  return (
      <div>
          <EarthquakeTable earthquakes={earthquakes} />
          <EarthquakeMap earthquakes={earthquakes} />
      </div>
  );
}

export default App;