import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import "leaflet/dist/leaflet.css";

const EarthquakeMap = ({ earthquakes }) => {
    return (
        <MapContainer center={[20, 0]} zoom={2} style={{ height: "500px" }}>
            <TileLayer url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" />

            {earthquakes.map((eq, index) => {
                console.log("EQ:", eq);

                if (eq.latitude == null || eq.longitude == null) return null;

                return (
                    <Marker
                        key={index}
                        position={[Number(eq.latitude), Number(eq.longitude)]}
                    >
                        <Popup>{eq.place}</Popup>
                    </Marker>
                );
            })}
        </MapContainer>
    );
};

export default EarthquakeMap;