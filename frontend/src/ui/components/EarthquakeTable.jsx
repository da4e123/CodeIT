import useEarthquakes from "../../hooks/useEarthquakes.js";

const EarthquakeTable = () => {
    const {
        earthquakes,
        loading,
        error,
        refreshEarthquakes,
        onDelete
    } = useEarthquakes();

    if (loading) return <p>Loading</p>;
    if (error) return <p>{error.message}</p>;

    return (
        <div style={{ width: "100%" }}>
            <table style={{ width: "100%", borderCollapse: "collapse" }} border="1" cellPadding="8">
                <thead>

                <tr>
                    <th colSpan="4" style={{
                        textAlign: "center",
                        fontSize: "25px",
                        padding: "12px",
                        fontWeight: "bold",
                        color: "white"
                    }}>
                        Earthquakes
                    </th>

                    <th style={{ textAlign: "right", padding: "12px" }}>
                        <button
                            onClick={refreshEarthquakes}
                            style={{
                                backgroundColor: "#007bff",
                                color: "white",
                                border: "none",
                                padding: "8px 12px",
                                borderRadius: "6px",
                                cursor: "pointer"
                            }}
                        >
                            Refresh
                        </button>
                    </th>
                </tr>

                <tr>
                    <th>Magnitude</th>
                    <th>Place</th>
                    <th>Time</th>
                    <th>Title</th>
                    <th>Actions</th>
                </tr>
                </thead>

                <tbody>
                {earthquakes.map((eq) => (
                    <tr key={eq.id}>
                        <td>{eq.magnitude}</td>
                        <td>{eq.place}</td>
                        <td>{new Date(eq.time).toLocaleString()}</td>
                        <td>{eq.title}</td>
                        <td>
                            <button
                                onClick={() => onDelete(eq.id)}
                                style={{
                                    backgroundColor: "rgba(255,0,0,0.79)",
                                    color: "white",
                                    border: "none",
                                    padding: "8px 12px",
                                    borderRadius: "6px",
                                    cursor: "pointer"
                                }}
                            >
                                Delete
                            </button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default EarthquakeTable;