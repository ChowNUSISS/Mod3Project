// inputStock.js
import styles from "./inputStock.module.css";

function InputStock({stockName, setStockName, newsFinResponse, sentimentScore, showSentimentScore}) {
    const handleChange = (event) => {
        setStockName(event.target.value.toUpperCase());
    };
    return (

        <div>
            <label className={styles.inputLabel}><b>Ticker: </b></label>
            <input className={styles.inputBox} value={stockName} onChange={handleChange} />
            <button onClick={newsFinResponse}>
                Search
            </button>
            <br />
            {showSentimentScore && 
            <div> 
                <b>Overall Score: </b>
                <sub 
                className={styles.scoreBox}
                >
                    {/* {(sentimentScore > 0 && sentimentScore < 0.35) ? '➡️' : '↗️'}  */}
                    {(sentimentScore).toFixed(3)}
                </sub>
            </div>}

        </div>
    );
};

export default InputStock;