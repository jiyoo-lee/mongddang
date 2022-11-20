import { useState,useEffect } from "react";
import { useNavigate } from "react-router-dom";
import GetAxios from "../../utils/axios/GetAxios";
import DeleteAxios from "../../utils/axios/DeleteAxios";


const AdminContest = () => {

    const navigate = useNavigate();
    const [ contests, setContests ] = useState([]);
    const [ keyword, setKeyword ] = useState("");


    useEffect(()=>{
        GetAxios('/admin/arts-center',{params:{}},(res)=>{
            console.log(res.data)
            setContests(res.data)
        })
    },[]) 
    
    const onChange = (e) =>{
        setKeyword(e.target.value)
    }

    const onSearch = () => {
        GetAxios('/admin/arts-center/'+keyword,{params:{}},(res)=>{setContests(res.data)})
    }


    return (
    <div className="admin">
        <ul className="admin_wrapper">
        <div className="logo_box">
        <img className='logo' src={process.env.PUBLIC_URL + `.././img/present_logo.png`} 
                onClick={()=> {navigate('/admin')}} alt='logo'/>
                </div>
                <li className="deth_menu"> Member </li>
                <li className="admin_menu" onClick={()=>{navigate('/admin')}}> 회원 조회 </li>
                <hr/>
                <li className="deth_menu"> Painting </li>
                <li className="admin_menu" onClick={()=>navigate('/admin/paintings')} > 그림 조회 </li>
                <hr/>
                <li className="deth_menu"> Contest </li>
                <li className="admin_menu" onClick={()=>window.location.reload()} > 공모전 관리 </li>
                <li className="admin_menu" onClick={()=>
                                                {sessionStorage.clear(); navigate('/'); }
                }> 로그아웃 </li>
        </ul>

    <section className="notice">
    <div className="page-title">
        <div className="container">
            <h3>공모전 조회</h3>
        </div>
    </div>
    <div id="board-search">
        <div className="container">
            <div className="search-window">
                    <div className="search-wrap">
                        <input type="text"  placeholder="공모전 제목을 입력해주세요." value={keyword} onChange={(e)=>onChange(e)}/>
                        <button type="submit" className="btn btn-dark" onClick={onSearch}>검색</button>
                    </div>
            </div>
        </div>
    </div>

    <div id="board-list">
        <div className="container">
            <table className="board-table">
                <thead>
                <tr>
                    <th scope="col" className="th-num">고유번호</th>
                    <th scope="col" className="th-num">작성자</th>
                    <th scope="col" className="th-title">제목</th>
                    <th scope="col" className="th-date">포스터</th>
                    <th scope="col" className="th-date">시작일자</th>
                    <th scope="col" className="th-date">종료일자</th>
                    <th scope="col" className="th-date">마감여부</th>
                    <th scope="col" className="th-date">작성날짜</th>
                   
                </tr>
                </thead>
                <tbody>
                {contests.length > 0 ? contests.map(contest => (
                    
                    <tr key={contest.contestId}>
                        <td>{contest.contestId}</td>
                        <td>{contest.memberId}</td>
                        <td>
                        {contest.title}
                        </td>
                        <td><img className="items_img" src={contest.posterUrl}/></td>
                        <td>{contest.startDay}</td>
                        <td>{contest.endDay}</td>
                        <td>{contest.deadline ? '마감' : <b>진행 중</b>}</td>
                        <td>{contest.createDatetime}</td>
                        
                </tr>
                
                )): <></>}
        
                </tbody>
            </table>
        </div>
    </div>

    </section>
    </div>
);
}

export default AdminContest;