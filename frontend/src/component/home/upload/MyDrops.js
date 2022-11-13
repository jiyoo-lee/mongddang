

const MyDrops = ({options}) => {
    return (
            <>
            <div className="upload_drops">
            <span className="home_message"> 새로운 드랍을 설정해주세요 </span>
            <br/>
            <br/>
            <br/>
            <select>
                {options.map(option=>(
                    <option key={option.id} value={option.id}>{option.name}</option>
                ))}
            </select>
               <input type='text'/>
            <br/>
               <input type='text'/>
            </div>
        </>
    );
}

export default MyDrops;